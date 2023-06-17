package com.example.springpractice.querydsl.entity;

import com.example.springpractice.querydsl.dto.EmployeeDto;
import com.example.springpractice.security.member.QMember;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
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

  @Test
  void 조건_여러개_검색() {
    Employee result = queryFactory
        .selectFrom(employee)
        .where(employee.name.like("%희%"),
            employee.age.eq(30))
        .fetchOne();

    assertThat(result).isEqualTo(ddang);
  }

  @Test
  void 결과_반환_메서드() {

    // 리스트
    List<Employee> resultList = queryFactory
        .selectFrom(employee)
        .where(employee.name.eq("땡희"))
        .fetch();

    // 단건
    Employee resultOne = queryFactory
        .selectFrom(employee)
        .where(employee.name.eq("땡희"))
        .fetchOne();

    // fetchFirst() : limit(1).fetchOne()
    Employee resultFirst = queryFactory
        .selectFrom(employee)
        .where(employee.name.eq("땡희"))
        .fetchFirst();

    // fetchResults() : 페이징 포함
    QueryResults<Employee> pageResult = queryFactory
        .selectFrom(employee)
        .where(employee.name.eq("땡희"))
        .fetchResults(); // deprecated..

    // fetchCount() : count 쿼리
    long count = queryFactory
        .selectFrom(employee)
        .where(employee.name.eq("땡희"))
        .fetchCount();
    // deprecated
  }

  @Test
  void projection_one_test() {
    Integer age = queryFactory.select(employee.age)
        .from(employee)
        .where(employee.name.eq("땡희"))
        .fetchOne();

    assertThat(age).isEqualTo(30);
  }

  @Test
  void projection_more_test() {
    Tuple result = queryFactory.select(employee.name, employee.age)
        .from(employee)
        .where(employee.name.like("땡희"))
        .fetchOne();

    String empName = result.get(employee.name);
    Integer empAge = result.get(employee.age);

    assertThat(empName).isEqualTo("땡희");
    assertThat(empAge).isEqualTo(30);
  }

  @Test
  void projection_dto_test() {
    EmployeeDto dto = queryFactory.select(Projections.fields(EmployeeDto.class,
            employee.id,
            employee.name,
            employee.age,
            employee.company.name.as("companyName"))  // 별칭 지정
        )
        .from(employee)
        .where(employee.name.like("땡희"))
        .fetchOne();

    assertThat(dto.getName()).isEqualTo("땡희");
    assertThat(dto.getAge()).isEqualTo(30);
    assertThat(dto.getCompanyName()).isEqualTo("희스터즈");
  }


}
