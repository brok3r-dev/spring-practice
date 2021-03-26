package com.spring.practice.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAdvice {
    private final Logger logger = LogManager.getLogger();

    @Around("execution(* com.spring.practice.controller.SchoolController.*(..))")
    public Object loggingAdvice(ProceedingJoinPoint point) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String className = point.getClass().toString();
        String methodName = point.getSignature().getName();
        Object[] objects = point.getArgs(); //request values

        logger.info("Method invoked: " + className + " / " + methodName);
        logger.info("arguments: " + mapper.writeValueAsString(objects));

        Object object = point.proceed();

        logger.info("response: " + mapper.writeValueAsString(object)); //response values

        return object;
    }
}
