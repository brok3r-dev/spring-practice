package com.spring.practice.common.config;

import com.spring.practice.common.filter.SchoolFilter;
import com.spring.practice.common.filter.StudentFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SchoolFilter> schoolFilterFilterRegistrationBean() {
        FilterRegistrationBean<SchoolFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SchoolFilter());
        registrationBean.addUrlPatterns("/school/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<StudentFilter> studentFilterFilterRegistrationBean() {
        FilterRegistrationBean<StudentFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new StudentFilter());
        registrationBean.addUrlPatterns("/student/*");
        return registrationBean;
    }
}