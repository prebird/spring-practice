package com.example.springpractice.SpringMvc.testStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CCCService {
  private final AAARepository aaaRepository;
  private final BBBRepository bbbRepository;

  public String print(String text) {
    return text;
  }
}
