package com.example.springpractice.SpringMvc.sameTypeBean;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/sameTypeBean")
@RestController
public class SameTypeBeanController {
  private final SameTypeBeanService sameTypeBeanService;

  @GetMapping()
  public ResponseEntity<String> test() {
    return ResponseEntity.ok(sameTypeBeanService.test());
  }

}
