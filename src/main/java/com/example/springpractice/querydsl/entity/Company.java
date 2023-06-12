package com.example.springpractice.querydsl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
@Entity
public class Company {
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }
}
