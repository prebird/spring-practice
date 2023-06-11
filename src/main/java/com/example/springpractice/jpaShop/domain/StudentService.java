package com.example.springpractice.jpaShop.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final int SIZE_PER_PAGE = 3;
    private final StudentRepository studentRepository;

    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(StudentDto::from).collect(Collectors.toList());
    }

    public Page<StudentDto> getByAge(Integer age, Optional<Integer> pageOptional, Optional<Direction> sortTypeOptional) {
        Integer page = pageOptional.orElse(0);
        Direction sortType = sortTypeOptional.orElse(Direction.ASC);
        PageRequest pageRequest = PageRequest.of(page, SIZE_PER_PAGE, sortType, "name");

        return studentRepository.findByAge(age, pageRequest).map(StudentDto::from);
    }
}
