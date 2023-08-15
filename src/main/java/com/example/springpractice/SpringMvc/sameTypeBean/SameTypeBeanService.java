package com.example.springpractice.SpringMvc.sameTypeBean;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SameTypeBeanService {
  private final BCryptPasswordEncoder bCryptForA;

  public String test() {
    return bCryptForA.encode("test");
  }
}
