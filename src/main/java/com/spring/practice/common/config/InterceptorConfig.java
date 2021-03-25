package com.spring.practice.common.config;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorConfig implements HandlerInterceptor {
    private final Logger logger = LogManager.getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("----- START COMMUNICATION -----");
        logger.info("Request URI: " + request.getRequestURI());

        if (!request.getHeader("Authorization").equals("SPRING_PRACTICE")) {
            logger.info("Response Status: 400");
            logger.info("----- END COMMUNICATION -----");
            throw new ApiException(ApiErrorCode.INVALID_AUTH_VALUE, HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("Response Status: " + response.getStatus());
        logger.info("----- END COMMUNICATION -----");
    }
}
