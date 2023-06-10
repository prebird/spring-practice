package com.example.springpractice.jpaShop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    List<Student> students;
    Student jinju;
    Student jinju2;
    Student jiyu;

    @BeforeEach
    void initData() {
        // 학생
        jinju = Student.of("김진주", 10);
        jinju2 = Student.of("김진주", 20);
        jiyu = Student.of("김지유", 20);

        studentRepository.save(jinju);
        studentRepository.save(jinju2);
        studentRepository.save(jiyu);

        // 반
        ClassRoom java = ClassRoom.of("자바반");
        ClassRoom design = ClassRoom.of("디자인반");
        classRoomRepository.save(java);
        classRoomRepository.save(design);

        // 학생에 반 정보 매핑
        jinju.setClassRoom(java);
        jinju2.setClassRoom(design);
        jiyu.setClassRoom(java);

        students = List.of(jinju, jinju2, jiyu);
    }

    @Test
    @DisplayName("JPQL 로 엔티티 조회")
    void getEntity() {
        String name = "김진주";
        int age = 10;

        List<Student> list = studentRepository.findStudentCustom(name, age);

        assertThat(list).hasSize(1);
        assertThat(list.get(0)).usingRecursiveComparison()
                .isEqualTo(jinju);
    }


    @Test
    @DisplayName("값 타입 조회")
    void findUsernameList() {
        List<String> usernameList = studentRepository.findStudentNameList();

        System.out.println(usernameList);
        assertThat(usernameList)
                .isEqualTo(students.stream().map(Student::getName)
                .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("조회 결과를 DTO로 반환")
    void dtoSelect() {
        List<StudentDto> dtoList = studentRepository.findStudentDto();
        System.out.println(dtoList);

        assertThat(dtoList).hasSize(3);
        assertThat(dtoList).extracting("className")
                .isEqualTo(students.stream().map(s -> s.getClassRoom().getName()).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("파라메터 이름으로 바인딩")
    void findByStudentName_parameterNameBinding() {
        List<Student> list = studentRepository.findByStudentName("지유");

        System.out.println(list);
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isEqualTo(jiyu);
    }

    @Test
    @DisplayName("컬헥션 파라메터")
    void findByStudentsNames_collectionParameter() {
        List<String> names = List.of("김진주", "김지유");

        List<Student> list = studentRepository.findByStudentNames(names);

        assertThat(list).hasSize(3);
        assertThat(list).usingRecursiveComparison()
                .isEqualTo(students);
    }

    @Test
    @DisplayName("")
    void template() {

    }
}
