package com.example.springpractice.SpringMvc.sameTypeBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringMvcConfig {
  @Bean
  public BCryptPasswordEncoder bCryptForA() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public BCryptPasswordEncoder bCryptForB() {
    return new BCryptPasswordEncoder();
  }

}
