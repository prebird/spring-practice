package com.example.springpractice.jpaShop.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ClassRoomRepositoryTest {
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Test
    void saveTest() {

        ClassRoom javaClass = ClassRoom.of("자바반");
        classRoomRepository.save(javaClass);
    }

}
