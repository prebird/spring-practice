package com.example.springpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyServiceImpl myService() {
        return new MyServiceImpl();
    }

    @Bean
    public BeanOne beanOne() {
        return new BeanOne();
    }

    @Bean
    public BeanTwo beanTwo() {
        return new BeanTwo(beanOne());
    }
}
