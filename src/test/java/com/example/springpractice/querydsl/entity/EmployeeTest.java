package com.example.springpractice.querydsl.entity;

import com.example.springpractice.security.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.springpractice.querydsl.entity.QEmployee.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeTest {
    @Autowired
    TestEntityManager testEm;
    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    // 테스트 데이터
    Employee jiyu;
    Employee jinju;
    Employee hee;
    Employee ddang;
    Company whiteHorse;
    Company heeSters;

    @BeforeEach
    void init() {
        // querydsl factory
        queryFactory = new JPAQueryFactory(em);

        // 테스트 데이터
        whiteHorse = testEm.persistAndFlush(Company.of("백마티비"));
        heeSters = testEm.persistAndFlush(Company.of("희스터즈"));

        jiyu = testEm.persistAndFlush(Employee.of("김지유", 10, whiteHorse));
        jinju = testEm.persistAndFlush(Employee.of("김진주", 20, whiteHorse));
        hee = testEm.persistAndFlush(Employee.of("최희령", 25, heeSters));
        ddang = testEm.persistAndFlush(Employee.of("땡희", 30, heeSters));
    }

    @Test
    void entity_select_test() {
        String jpql =
                "select e from Employee e " +
                "where e.name = :name";
        List<Employee> employees = em.createQuery(jpql, Employee.class)
                .setParameter("name", "땡희")
                .getResultList();

        assertThat(employees).contains(ddang);
    }

    @Test
    void entity_select_querydsl() {
        Employee ddang = testEm.persistAndFlush(Employee.of("땡희"));
        // when
        //QEmployee e = new QEmployee("e"); // static import 방식으로 변경

        Employee result = queryFactory
                .select(employee)
                .from(employee)
                .where(employee.name.eq("땡희"))     // 파라메터 바인딩
                .fetchOne();

        assertThat(result).usingRecursiveComparison().isEqualTo(ddang);
    }

    @Test
    void 리스트_조회() {
        List<Employee> result = queryFactory
                .selectFrom(employee)
                .fetch();
        assertThat(result).hasSize(4);
    }
}
