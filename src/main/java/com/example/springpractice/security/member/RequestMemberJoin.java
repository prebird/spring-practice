package com.example.springpractice.security.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RequestMemberJoin {
    private String username;
    private String password;
    private Role role;
}
