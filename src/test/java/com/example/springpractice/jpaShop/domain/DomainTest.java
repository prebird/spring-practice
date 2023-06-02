package com.example.springpractice.jpaShop.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DomainTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    void entityTest() {
        ClassRoom javaClass = ClassRoom.of("자바반");
        em.persist(javaClass);

        Student jiyu = Student.of("김지유");
        Student jinjoo = Student.of("김진주");
        em.persist(jiyu);
        em.persist(jinjoo);

        List<Student> students = List.of(jiyu, jinjoo);

        javaClass.setStudents(students);
    }
}
