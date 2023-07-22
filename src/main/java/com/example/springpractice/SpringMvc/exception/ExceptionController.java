package com.example.springpractice.SpringMvc.exception;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

  @GetMapping("/{id}")
  public ResponseEntity<String> error400Test(@PathVariable Long id) {
    if (id < 0) {
      throw new IllegalArgumentException(ErrorCode.ID_MUST_MOR_THAN_0.getCode());
    }

    return ResponseEntity.ok(id.toString());
  }
}
