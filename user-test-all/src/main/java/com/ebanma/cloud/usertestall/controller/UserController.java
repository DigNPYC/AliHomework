package com.ebanma.cloud.usertestall.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;

import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 肖露
 * @version $ Id: UserController, v 0.1 2023/03/17 10:23 banma-0241 Exp $
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return Result.success();

        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }

    }

    @PutMapping
    public Result update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if (update == 1) {
            return Result.success();

        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }

    }

    @DeleteMapping
    public Result delete(@PathVariable Long id) {
        int delete = userService.delete(id);
        if (delete == 1) {
            return Result.success();

        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @GetMapping
    public Result<PageResult> query(Integer pageNo, Integer pageSize, UserQueryDTO queryDTO) {
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(queryDTO);
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData()).map(List::stream).orElseGet(Stream::empty)
            .map(userDTO -> {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(userDTO, userVO);
                userVO.setPassword("************");
                return userVO;
            }).collect(Collectors.toList());
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);
        return Result.success(result);

    }

}
