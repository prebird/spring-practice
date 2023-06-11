package com.example.springpractice.jpaShop;

import com.example.springpractice.jpaShop.domain.StudentDto;
import com.example.springpractice.jpaShop.domain.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> searchAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
}
