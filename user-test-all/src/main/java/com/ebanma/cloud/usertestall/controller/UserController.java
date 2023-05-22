package com.ebanma.cloud.usertestall.controller;

import com.ebanma.cloud.usertestall.domain.common.ErrorCodeEnum;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.service.ExcelService;
import com.ebanma.cloud.usertestall.service.UserService;
import com.ebanma.cloud.usertestall.util.InsertValidationGroup;
import com.ebanma.cloud.usertestall.util.UpdateValidationGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ExcelService excelService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 新增用户
     *
     * @param userDTO 用户dto
     * @return {@link Result}
     */
    @PostMapping
    //设置为true,方法调用后立即清空所有缓存
    @CacheEvict(cacheNames = "user-cache", allEntries = true)
    public Result save(@Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if(save == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 更新用户信息
     *
     * @param id      id
     * @param userDTO 用户dto
     * @return {@link Result}
     */
    @PutMapping("/{id}")
    public Result update(@NotNull(message = "用户id不能为空") @PathVariable("id") Long id,
                         @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if(update == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 删除用户信息
     *
     * @param id id
     * @return {@link Result}
     */
    @DeleteMapping("/{id}")
    public Result delete(@NotNull(message = "用户id不能为空") @PathVariable("id") Long id) {
        int delete = userService.delete(id);
        if(delete == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 分页查询用户信息
     *
     * @param pageNo   页面编号
     * @param pageSize 页面大小
     * @param queryDTO 查询dto
     * @return {@link Result}
     */
    @GetMapping
    @Cacheable(cacheNames = "user-cache", key = "#pageNo")
    public Result<PageResult> query(Integer pageNo, Integer pageSize, UserQueryDTO queryDTO) {
        //构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(queryDTO);

        if (logger.isInfoEnabled()) {
            logger.info("未使用缓存");
        }

        //查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        if (pageResult != null) {
            //实体转换
            List<UserVO> userVOList = Optional
                    .ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserVO userVO = new UserVO();
                        BeanUtils.copyProperties(userDTO, userVO);
                        return userVO;
                    })
                    .collect(Collectors.toList());
            PageResult<List<UserVO>> result = new PageResult<>();
            BeanUtils.copyProperties(pageResult, result);
            result.setData(userVOList);
            return Result.success(result);
        }
        return Result.success(null);
    }

    /**
     * GET
     * 查询数据并导出
     */
    @GetMapping("/export")
    public Result<Boolean> export(@NotEmpty String filename, UserQueryDTO queryDTO) {
        //excelService.export(filename, queryDTO);
        excelService.asyncExport(filename, queryDTO);
        return Result.success(Boolean.TRUE);
    }

}
