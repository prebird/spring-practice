package com.example.springpractice.jpaShop.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void queryTest() {
        Student jinju = Student.of("김진주", 10);
        Student jinju2 = Student.of("김진주", 20);
        Student jiyu = Student.of("김지유", 20);

        studentRepository.save(jinju);
        studentRepository.save(jinju2);
        studentRepository.save(jiyu);

        List<Student> result = studentRepository.findStudentCustom("김진주", 10);
        assertThat(result).hasSize(1);
        assertThat(result).contains(jinju);
    }
}
