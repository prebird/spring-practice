package com.example.springpractice.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("-------securityFilterChain-------");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/mamegers/**").hasRole("MANAGER")
                .antMatchers("/partners/**").hasRole("partners")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}
