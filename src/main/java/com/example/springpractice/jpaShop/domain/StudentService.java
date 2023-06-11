package com.example.springpractice.jpaShop.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(StudentDto::from).collect(Collectors.toList());
    }
}
