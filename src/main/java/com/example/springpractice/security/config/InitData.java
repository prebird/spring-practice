package com.example.springpractice.security.config;

import com.example.springpractice.security.LoginService;
import com.example.springpractice.security.member.RequestMemberJoin;
import com.example.springpractice.security.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    private final LoginService loginService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initMemberData();
    }

    private void initMemberData() {
        RequestMemberJoin user = RequestMemberJoin.builder()
                .username("user")
                .password("12")
                .role(Role.USER).build();

        RequestMemberJoin manager = RequestMemberJoin.builder()
                .username("manager")
                .password("12")
                .role(Role.MANAGER).build();

        RequestMemberJoin partner = RequestMemberJoin.builder()
                .username("partner")
                .password("12")
                .role(Role.PARTNER).build();

        loginService.join(user);
        loginService.join(manager);
        loginService.join(partner);
    }
}
