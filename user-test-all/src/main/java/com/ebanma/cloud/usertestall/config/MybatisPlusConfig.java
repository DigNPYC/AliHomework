package com.ebanma.cloud.usertestall.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 肖露
 * @version $ Id: MybatisPlusConfig, v 0.1 2023/03/17 10:14 banma-0241 Exp $
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }
}
