package com.example.springpractice.jpaShop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String className;
}
