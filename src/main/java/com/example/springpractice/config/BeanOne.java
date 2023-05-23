package com.example.springpractice.config;

public class BeanOne {
    public void init() {
        System.out.println("beanOne을 초기화 합니다...");
    }

    public void destroy() {
        System.out.println("beanOne을 destory 합니다...");
    }
}
