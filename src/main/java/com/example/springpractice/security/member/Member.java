package com.example.springpractice.security.member;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
