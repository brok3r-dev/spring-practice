package com.spring.practice.common.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class CommonFilter implements Filter {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("----- COMMON FILTER -----");
        logger.info("Server Name: " + request.getServerName());

        chain.doFilter(request, response);
    }
}