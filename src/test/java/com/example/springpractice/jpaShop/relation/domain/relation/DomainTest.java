package com.example.springpractice.jpaShop.relation.domain.relation;

import com.example.springpractice.jpaShop.relation.domain.ClassRoom;
import com.example.springpractice.jpaShop.relation.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
@Sql(value = {"/truncate.sql"})
class DomainTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    void oneToManyInserTest() {
        ClassRoom javaClass = ClassRoom.of("자바반");
        em.persist(javaClass);

        Student jiyu = Student.of("김지유");
        Student jinjoo = Student.of("김진주");
        em.persist(jiyu);
        em.persist(jinjoo);

        List<Student> students = List.of(jiyu, jinjoo);

        javaClass.setStudents(students);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void manyToOneInsertTest() {
        ClassRoom javaClass = ClassRoom.of("자바반");
        em.persist(javaClass);

        Student jiyu = Student.of("김지유");
        Student jinjoo = Student.of("김진주");
        em.persist(jiyu);
        em.persist(jinjoo);

        jiyu.setClassRoom(javaClass);
        jinjoo.setClassRoom(javaClass);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void owner_test() {
        // 반 추가
        ClassRoom javaClass = ClassRoom.of("자바반");
        ClassRoom englishClass = ClassRoom.of("영어반");
        em.persist(javaClass);
        em.persist(englishClass);

        // 학생 추가
        Student student1 = Student.of("김지유");
        Student student2 = Student.of("김민수");
        em.persist(student1);
        em.persist(student2);

        // 반 등록
        student1.setClassRoom(javaClass);        // 자바반으로 등록
        student2.setClassRoom(javaClass);        // 자바반으로 등록

        // 학생 등록
        javaClass.addStudent(student1);     // 자바반으로 등록
        englishClass.addStudent(student2);  // 영어반으로 등록
    }
}
