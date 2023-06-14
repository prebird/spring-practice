package com.example.springpractice.querydsl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "age"})
@Entity
public class Employee {
    @Id @GeneratedValue
    @Column(name = "Employee_id")
    private Long id;
    private String name;
    private Integer age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee(String name, Integer age, Company company) {
        this.name = name;
        this.age = age;
        if (company != null) {
            changeCompany(company);
        }
    }

    public static Employee of(String name, Integer age, Company company) {
        return new Employee(name, age, company);
    }

    public static Employee of(String name, Integer age) {
        return new Employee(name, age, null);
    }

    public static Employee of(String name) {
        return new Employee(name, null, null);
    }

    public void changeCompany(Company company) {
        this.company = company;
        company.getEmployees().add(this);
    }
}
