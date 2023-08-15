package com.example.springpractice.SpringMvc.testStrategy;


import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CCCServiceTest extends RepositoryTest {
  @Autowired
  AAARepository aaaRepository;
  @Autowired
  BBBRepository bbbRepository;
  CCCService cccService;

  @Test
  void test() {
    // given
    cccService = new CCCService(aaaRepository, bbbRepository);

    // when
    String result = cccService.print("테스트");

    // then
    assertThat(cccService).isNotNull();
    assertThat(result).isEqualTo("테스트");
  }
}
