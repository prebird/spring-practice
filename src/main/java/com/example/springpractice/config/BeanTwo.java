package com.example.springpractice.config;

public class BeanTwo {
    private final BeanOne beanOne;

    public BeanTwo(BeanOne beanOne) {
        this.beanOne = beanOne;
    }

    public void init() {
        System.out.println("beanTwo을 초기화 합니다...");
    }

    public void close() {
        System.out.println("beanTwo을 cleanUp 합니다...");
    }
}
