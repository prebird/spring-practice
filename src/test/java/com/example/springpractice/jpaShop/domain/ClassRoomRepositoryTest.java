package com.example.springpractice.jpaShop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ClassRoomRepositoryTest {
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void saveTest() {
        ClassRoom javaClass = ClassRoom.of("자바반");
        classRoomRepository.save(javaClass);

        List<ClassRoom> classes = classRoomRepository.findAll();
        System.out.println(classes);
    }

    @Test
    @DisplayName("id로 반을 조회한다.")
    void findById_returnClassRoom() {
        ClassRoom javaClass = ClassRoom.of("자바반");
        ClassRoom savedOne = classRoomRepository.save(javaClass);

        ClassRoom findClass = classRoomRepository.findById(savedOne.getId())
                .orElseThrow(() -> new RuntimeException("반 조회에 실패했습니다."));

        assertThat(findClass.getName()).isEqualTo("자바반");
    }

    @Test
    @DisplayName("id로 반을 조회한다.")
    void findById_returnClassRoom2() {
        ClassRoom javaClass = entityManager.persistAndFlush(ClassRoom.of("자바반"));

        ClassRoom findClass = classRoomRepository.findById(javaClass.getId())
                .orElseThrow(() -> new RuntimeException("반 조회에 실패했습니다."));

        assertThat(findClass.getName()).isEqualTo("자바반");
    }

}
