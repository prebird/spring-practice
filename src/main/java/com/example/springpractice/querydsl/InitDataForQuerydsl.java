package com.example.springpractice.querydsl;

import com.example.springpractice.querydsl.entity.Company;
import com.example.springpractice.querydsl.entity.Employee;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("querydslLocal")
@Component
@RequiredArgsConstructor
public class InitDataForQuerydsl {

  private final InitEmployeeService initEmployeeService;

  @PostConstruct
  public void init() {
    initEmployeeService.init();
  }

  @Component
  static class InitEmployeeService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init() {
      Company companyA = Company.of("companyA");
      Company companyB = Company.of("companyB");
      em.persist(companyA);
      em.persist(companyB);

      for (int i = 0; i < 100; i++) {
        Company selectedCompany = i % 2 == 0 ? companyA : companyB;
        em.persist(Employee.of("employee"+i, i, selectedCompany));
      }


    }
  }
}
