package com.example.springpractice.querydsl.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Employee {
    @Id @GeneratedValue
    @Column(name = "Employee_id")
    private Long id;

    private String name;
}
