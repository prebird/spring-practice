package com.example.springpractice.jpaShop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(of = {"id", "name", "age"}) // toString은 가급적 연관관계가 앖는 필드만 수행합니다.
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    private Student(String name) {
        this(name, 0);
    }
    private Student(String name, Integer age) {
        this(name, age, null);
    }
    private Student(String name, Integer age, ClassRoom classRoom) {
        this.name = name;
        this.age = age;
        if (this.classRoom != null) {
            changeClassRoom(classRoom);
        }
    }

    /**
     * 반을 변경합니다.
     * 연관관계 편의 메서드: 양방향 연관관계인 ClassRoom에 학생을 추가해 줍니다.
     * @param classRoom
     */
    private void changeClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
        classRoom.getStudents().add(this);
    }

    public static Student of(String name) {
        return new Student(name);
    }

    public static Student of(String name, Integer age) {
        return new Student(name, age);
    }

    public static Student of(String name, Integer age, ClassRoom classRoom) {
        return new Student(name, age, classRoom);
    }

}
