package com.ebanma.cloud.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
