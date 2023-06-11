package com.example.springpractice.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityTestController {

    private final IAuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public ResponseEntity<String> main() {
        return ResponseEntity.ok("main");
    }
    @GetMapping("/users")
    public ResponseEntity<String> users() {
        return ResponseEntity.ok("users");
    }

    @GetMapping("/partners")
    public ResponseEntity<String> partners() {
        return ResponseEntity.ok("partners");
    }

    @GetMapping("/managers")
    public ResponseEntity<String> managers() {
        return ResponseEntity.ok("managers");
    }

    @GetMapping("/getAuth")
    public ResponseEntity<String> getAuth() {
        log.info("------ getAuth -------");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = auth.getName();       // 인증 사용자 명
        MemberDetails principal = (MemberDetails)auth.getPrincipal(); // 인증 사용자 객체

        System.out.println(principal.getUsername());
        System.out.println(principal.getPassword());
        System.out.println(principal.getAuthorities());

        return ResponseEntity.ok("auth");
    }

    @GetMapping("/getPrincipalInController")
    public String getPrincipalInController(Principal principal) {
        return principal.getName();
    }
    @GetMapping("/getAuthInController")
    public String getAuthInController(Authentication authentication) {
        authentication.getAuthorities();
        return authentication.getName();
    }

    @GetMapping("/getAuthByBean")
    public String getAuthByBean() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

}
