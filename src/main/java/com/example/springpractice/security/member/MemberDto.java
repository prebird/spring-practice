package com.example.springpractice.security.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDto {
    private String username;
    private Role role;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .username(member.getUsername())
                .role(member.getRole())
                .build();
    }
}
