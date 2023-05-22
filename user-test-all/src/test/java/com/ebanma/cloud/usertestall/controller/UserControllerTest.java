package com.ebanma.cloud.usertestall.controller;

import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.service.ExcelService;
import com.ebanma.cloud.usertestall.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ExcelService excelService;

    @InjectMocks
    private UserController userController;

    @Test
    public void should_invoke_user_service_to_save_user_success() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("张亮");
        userDTO.setPassword("1234");
        userDTO.setEmail("1234@sina.com");
        userDTO.setAge(10);
        userDTO.setPhone("13156568989");
        when(userService.save(userDTO)).thenReturn(1);
        //when
        userController.save(userDTO);
        //then
        verify(userService).save(any(UserDTO.class));
    }

    @Test
    public void should_invoke_user_service_to_update_user_success() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username1");
        userDTO.setPassword("password1");
        userDTO.setEmail("1234@sina.com");
        userDTO.setAge(10);
        userDTO.setPhone("13156568989");
        Long id = 1L;
        when(userService.update(id, userDTO)).thenReturn(1);
        //when
        userController.update(id, userDTO);
        //then
        verify(userService).update(any(Long.class), any(UserDTO.class));
    }

    @Test
    public void should_invoke_user_service_to_delete_user_success() {
        //given
        Long id = 1L;
        when(userService.delete(id)).thenReturn(1);
        //when
        userController.delete(id);
        //then
        verify(userService).delete(any(Long.class));
    }

    @Test
    public void should_invoke_user_service_to_query_user_success() {
        //given
        Integer pageNo = 1;
        Integer pageSize = 10;
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setUsername("username1");
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(queryDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("王老吉");
        userDTO.setAge(2);
        userDTO.setPhone("123456789");
        userDTO.setEmail("123@w.com");
        List<UserDTO> userDTOList = Arrays.asList(userDTO);
        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        pageResult.setData(userDTOList);
        pageResult.setPageNo(1);
        pageResult.setPageSize(10);
        when(userService.query(any())).thenReturn(pageResult);
        //when
        userController.query(pageNo, pageSize, queryDTO);
        verify(userService).query(any(PageQuery.class));
    }

    @Test
    public void should_invoke_user_service_to_export_success() {
        //given
        String filename = "user";
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setUsername("杨国福");
        doNothing().when(excelService).asyncExport(filename, queryDTO);
        //when
        userController.export(filename, queryDTO);
        //then
        verify(excelService).asyncExport(any(String.class), any(UserQueryDTO.class));
    }
}