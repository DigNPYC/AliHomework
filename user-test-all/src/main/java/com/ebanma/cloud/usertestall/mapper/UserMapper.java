package com.ebanma.cloud.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 肖露
 * @version $ Id: UserMapper, v 0.1 2023/03/20 16:15 banma-0241 Exp $
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
