package com.example.springpractice.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/users/1")
    public ResponseEntity<String> users() {
        return ResponseEntity.ok("users");
    }

    @GetMapping("/partners/1")
    public ResponseEntity<String> partners() {
        return ResponseEntity.ok("partners");
    }

    @GetMapping("/managers/1")
    public ResponseEntity<String> managers() {
        return ResponseEntity.ok("managers");
    }
}
