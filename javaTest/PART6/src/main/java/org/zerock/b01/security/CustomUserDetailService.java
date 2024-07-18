package org.zerock.b01.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
// @RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // username으로 사용자 인증 -- 사용자 정보 로딩
    /*
    ** Security에서는 사용자 ID를 "username"이라고 한다.
        1. username으로 사용자 인증 후 사용자 정보 로딩 -- id 인증 먼저 > loadUserByUsername(String username)
        2. 로딩한 정보의 패스워드로 사용자 패스워드와 검증 -- id에 맞는 패스워드 검증
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: " + username);

        // UserDetails interface -> 구현체로 User 클래스 객체 생성
        UserDetails userDetails = User.builder()
                .username("user1")  // 사용자 id(username)
                .password(passwordEncoder.encode("1111"))   // 패스워드
                .authorities("ROLE_USER")   // 인가(권한)
                .build();

        return userDetails; // 사용자 정보(패스워드 검증, 인가(권한) 등등)
    }
}
