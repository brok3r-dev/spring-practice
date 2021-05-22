//package com.spring.practice.common.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatasourceConfig {
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.classname}")
//    private String className;
//
//    @Lazy
//    @Bean(destroyMethod = "close")
//    DataSource getDatasource() {
//        final HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setUsername(username);
//        hikariConfig.setPassword(password);
//        hikariConfig.addDataSourceProperty("url", url);
//        hikariConfig.setDataSourceClassName(className);
//        hikariConfig.setLeakDetectionThreshold(2000);
//        hikariConfig.setPoolName("spring_pool");
//        hikariConfig.setMaximumPoolSize(10);
//
//        return new HikariDataSource(hikariConfig);
//    }
//}
