package com.example.springpractice.jpaShop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLASS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString(of = {"id", "name"})
public class ClassRoom {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany()
    @JoinColumn(name = "class_id")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public static ClassRoom of(String name) {
        return ClassRoom.builder()
                .name(name)
                .build();
    }
}
