package com.example.springpractice.security;

import com.example.springpractice.security.member.Member;
import com.example.springpractice.security.member.MemberDto;
import com.example.springpractice.security.member.MemberRepository;
import com.example.springpractice.security.member.RequestMemberJoin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        return MemberDetails.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }

    public MemberDto join(RequestMemberJoin request) {
        return MemberDto.from(
                memberRepository.save(Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build()));
    }
}
