package com.example.springpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfigForConfig {

    @Bean
    public MyServiceImpl myService() {
        return new MyServiceImpl();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanOne beanOne() {
        return new BeanOne();
    }

    @Bean(initMethod = "init")
    public BeanTwo beanTwo() {
        return new BeanTwo(beanOne());
    }

    @Bean
    @Scope("prototype")
    public Encryptor encryptor() {
        return new Encryptor();
    }
}
