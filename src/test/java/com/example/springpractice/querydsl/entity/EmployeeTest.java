package com.example.springpractice.querydsl.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeTest {
    @Autowired
    TestEntityManager testEm;

    @Autowired
    EntityManager em;

    @Test
    void entity_select_test() {
        Employee ddang = testEm.persistAndFlush(Employee.of("땡희"));

        String jpql =
                "select e from Employee e " +
                "where e.name = :name";
        List<Employee> employees = em.createQuery(jpql, Employee.class)
                .setParameter("name", "땡희")
                .getResultList();

        assertThat(employees).contains(ddang);
    }
}
