package com.example.springpractice.security.config;

import com.example.springpractice.security.auth.AuthService;
import com.example.springpractice.security.member.RequestMemberJoin;
import com.example.springpractice.security.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthService authService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initMemberData();
    }

    private void initMemberData() {
        RequestMemberJoin user = RequestMemberJoin.builder()
                .username("user")
                .password("12")
                .role(Role.ROLE_USER).build();

        RequestMemberJoin manager = RequestMemberJoin.builder()
                .username("manager")
                .password("12")
                .role(Role.ROLE_MANAGER).build();

        RequestMemberJoin partner = RequestMemberJoin.builder()
                .username("partner")
                .password("12")
                .role(Role.ROLE_PARTNER).build();

        authService.join(user);
        authService.join(manager);
        authService.join(partner);
    }
}
