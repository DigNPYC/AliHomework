package com.ebanma.cloud.usertestall.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserExportDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.service.ExcelService;
import com.ebanma.cloud.usertestall.service.FileService;
import com.ebanma.cloud.usertestall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    public void export(String filename, UserQueryDTO userQueryDTO) {
        // 1. 实现数据查询并导出到excel中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        dataToExcel(outputStream, userQueryDTO);
        // 2. 实现excel文件上传
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        fileService.upload(inputStream, filename);
    }

    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(String filename, UserQueryDTO userQueryDTO) {
        export(filename, userQueryDTO);
    }

    private void dataToExcel(ByteArrayOutputStream outputStream, UserQueryDTO queryDTO) {
        // 1.需要创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream, UserExportDTO.class).build();
        // 2.分页查询加载数据
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setQuery(queryDTO);
        pageQuery.setPageSize(2);
        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        do {
            pageQuery.setPageNo(++pageNo);
            if(logger.isInfoEnabled()) {
                logger.info("开始导出第[ {} ]页数据", pageNo);
            }
            pageResult = userService.query(pageQuery);
            List<UserExportDTO> exportDTOS = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO exportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, exportDTO);
                        return exportDTO;
                    }).collect(Collectors.toList());
            // 3.导出分页数据到excel
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第" + pageNo + "页").build();
            excelWriter.write(exportDTOS, writeSheet);
            if(logger.isInfoEnabled()) {
                logger.info("结束导出第[ {} ]页数据", pageNo);
            }
        }while (pageResult.getPageNum() > pageNo);
        // 4.关闭excel文件流
        excelWriter.finish();
        if(logger.isInfoEnabled()) {
            logger.info("完成导出");
        }
    }
}
