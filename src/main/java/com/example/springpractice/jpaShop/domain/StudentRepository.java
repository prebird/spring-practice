package com.example.springpractice.jpaShop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.name = :name and s.age = :age")
    List<Student> findStudentCustom(@Param("name") String studentName, @Param("age") int age);
}
