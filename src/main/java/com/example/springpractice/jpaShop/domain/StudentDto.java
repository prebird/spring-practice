package com.example.springpractice.jpaShop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String className;

    public static StudentDto from(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .className(student.getClassRoom().getName())
                .build();
    }
}
