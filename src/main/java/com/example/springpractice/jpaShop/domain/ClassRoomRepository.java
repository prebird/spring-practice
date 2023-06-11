package com.example.springpractice.jpaShop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    Optional<ClassRoom> findByName(String name);
}
