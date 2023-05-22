package com.ebanma.cloud.usertestall.interceptor;

import com.ebanma.cloud.usertestall.domain.common.ErrorCodeEnum;
import com.ebanma.cloud.usertestall.exception.BusinessException;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    /**
     * 速度限制器(QPS)
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(1);
    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.tryAcquire()) {
            logger.error("系统已经限流");
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        return true;
    }
}
