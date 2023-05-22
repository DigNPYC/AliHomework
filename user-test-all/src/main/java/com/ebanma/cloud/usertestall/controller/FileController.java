package com.ebanma.cloud.usertestall.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ebanma.cloud.usertestall.domain.common.ErrorCodeEnum;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.exception.BusinessException;
import com.ebanma.cloud.usertestall.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @RequestMapping("/upload")
    public Result<String> upload(@NotNull MultipartFile file) {
        try {
            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("文件上传失败" + e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAIL);
        }
        return Result.success(file.getOriginalFilename());
    }
}
