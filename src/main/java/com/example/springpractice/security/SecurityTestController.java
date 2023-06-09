package com.example.springpractice.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {
    @GetMapping("/")
    public ResponseEntity<String> main() {
        return ResponseEntity.ok("main");
    }
    @GetMapping("/users")
    public ResponseEntity<String> users() {
        return ResponseEntity.ok("users");
    }

    @GetMapping("/partners")
    public ResponseEntity<String> partners() {
        return ResponseEntity.ok("partners");
    }

    @GetMapping("/managers")
    public ResponseEntity<String> managers() {
        return ResponseEntity.ok("managers");
    }
}
