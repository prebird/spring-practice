package com.example.springpractice.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/permitted")
    public ResponseEntity<String> permitted() {
        return ResponseEntity.ok("connected");
    }

    @GetMapping("/rejected")
    public ResponseEntity<String> rejected() {
        return ResponseEntity.ok("connected");
    }
}
