package com.ebanma.cloud.usertestall.config;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;


/**
 * @author 肖露
 * @version $ Id: CaffeineCacheConfig, v 0.1 2023/03/22 14:37 banma-0241 Exp $
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {
    private static  final Logger logger= LoggerFactory.getLogger(CaffeineCacheConfig.class);
    @Bean("cacheManager")
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches=new ArrayList<>();
        caches.add(new CaffeineCache("user-cache",
            Caffeine.newBuilder().maximumSize(1000).expireAfterAccess(120, TimeUnit.SECONDS).build()));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
