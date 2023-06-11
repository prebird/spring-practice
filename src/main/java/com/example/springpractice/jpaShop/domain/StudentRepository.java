package com.example.springpractice.jpaShop.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByAge(int age, Pageable pageable);

    @Query("select s from Student s where s.name = :name and s.age = :age")
    List<Student> findStudentCustom(@Param("name") String studentName, @Param("age") int age);

    @Query("select s.name from Student s")
    List<String> findStudentNameList();

    @Query("select new com.example.springpractice.jpaShop.domain.StudentDto(s.id, s.name, s.age, c.name) " +
            "from Student s join s.classRoom c")
    List<StudentDto> findStudentDto();

    @Query("select s from Student s where s.name like %:studentName%")
    List<Student> findByStudentName(@Param("studentName") String studentName);

    @Query("select s from Student s where s.name in :names")
    List<Student> findByStudentNames(List<String> names);
}
