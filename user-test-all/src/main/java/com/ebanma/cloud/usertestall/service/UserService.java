package com.ebanma.cloud.usertestall.service;

import java.util.List;

import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import org.springframework.stereotype.Service;

/**
 * @author 肖露
 * @version $ Id: UserService, v 0.1 2023/03/20 8:39 banma-0241 Exp $
 */

public interface UserService {
    int save(UserDTO userDTO);

    int update(Long id, UserDTO userDTO);

    int delete(Long id);

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
