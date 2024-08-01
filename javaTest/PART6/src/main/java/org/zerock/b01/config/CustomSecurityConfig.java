package org.zerock.b01.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.b01.security.handler.Custom403Handler;
import org.zerock.b01.security.CustomUserDetailService;
import org.zerock.b01.security.handler.CustomSocialLoginSuccessHandler;

import javax.sql.DataSource;

@Log4j2
@Configuration  // 컴포넌트 중 환경설정을 담당하는 컴포넌트라고 Spring에게 알리는 어노테이션
@RequiredArgsConstructor    // final 필드를 매개변수로 받는 생성자 생성
// @EnableMethodSecurity -- Spring Security에서 메소드 수준의 보안을 활성화하겠다고 지정
// prePostEnabled = true -- @Pre/PostAuthorize : 사전/사후 권한 체크 어노테이션 사용하겠다고 지정
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    // remember-me 기능 위해 데이터베이스 연결(DataSource) 객체와 사용자 인증 정보 객체 자동 주입
    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 소셜 로그인 성공 후 동작할 핸들러
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    // SecurityFilterChain -- HTTP 요청에 대한 보안 필터 체인을 정의하는 객체
    // HttpSecurity -- 보안 구성을 위한 메서드 제공
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("--------------configure--------------");

        // HttpSecurity http -- Security 동작 설정 (메서드를 이용하여)
        http
                .formLogin(frm -> { // 폼 기반 로그인 인증 메커니즘 사용 : id(username), password로 인증하는 방식
                    frm.loginPage("/member/login"); // -- 사용자 정의 로그인 페이지로 경로 설정

                })
                // OAuth2 방식을 사용한 소셜 로그인
                .oauth2Login(oauth -> {
                    oauth.loginPage("/member/login")   // -- oauth2 로그인할 수 있는 페이지 경로 설정
                            .successHandler(authenticationSuccessHandler());
                })

                .csrf(  // CSRF 보호 기능 비활성화
                        AbstractHttpConfigurer::disable // 람다 메서드 참조 방식
                )
                .rememberMe(remem -> {  // 자동 로그인 기능 설정
                    remem
                            .rememberMeParameter("remember-me") // remember-me 기능을 활성화하는데 사용되는 요청 파라미터 이름
                            .key("12345678")    // remember-me 토큰을 생성할 때 사용되는 키를 설정 -> 토큰의 유효성을 검증하는 데 사용
                            .tokenRepository(persistentTokenRepository())   // 토큰 저장소를 설정 (토큰을 저장하고 검색하는 데 사용되는 데이터를 저장하고 있는 부분)
                            .userDetailsService(userDetailService) // 인증 과정에서 사용자의 세부 정보를 로드하는 데 사용되는 userDetailsService 설정
                            .tokenValiditySeconds(60*60*24*30) // 토큰의 유효 기간 초 단위 설정
                    // 60초*60분*24시간*30일 => 30일 설정
                            .alwaysRemember(false); // remember-me 옵션을 명시적으로 선택하지 않아도 항상 기능을 활성화할지 여부 설정
                    // false -- remember-me를 선택해야만 자동 로그인 기능을 사용하겠다고 활성화
                })
                .exceptionHandling(ex -> {  // Spring Security 예외 처리 구성
                    ex.accessDeniedHandler(accessDeniedHandler());
                    // accessDeniedHandler() 사용자 정의 접근 거부 핸들러를 반환하는 메서드 호출
                })
        ;
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    // WebSecurityCustomizer -- Spring Security 웹 보안 설정을 커스터마이징 할 때 사용
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("----------web configure----------");

        // WebSecurityCustomizer 반환 타입은 Interface이기 때문에 람다 표현을 사용하여 구현체 반환
        // ignoring() -- 특정 경로 또는 요청을 Spring Security의 보안 필터 체인에서 제외하기 위해 사용
        // requestMatchers() -- 특정 요청 패턴에 대한 매처(matcher)를 정의
        // PathRequest.toStaticResources().atCommonLocations() : 정적 리소스에 대한 일반적인 위치를 나타내는 RequestMatcher를 생성
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean   // remember-me 기능을 위한 PersistentTokenRepository 설정 -- 토큰 저장소로 사용될 DB
    // PersistentTokenRepository -- remember-me 인증 토큰을 저장하고 검색하는 데 사용
    public PersistentTokenRepository persistentTokenRepository() {
        // PersistentTokenRepository 인터페이스의 구현체로, JDBC를 사용하여 데이터베이스에 remember-me 토큰 저장 및 관리
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        // JdbcTokenRepositoryImpl에 DataSource를 설정
        // dataSource -- 현재 연결되어 있는 데이터베이스 연결 정보를 담고 있는 객체
        repo.setDataSource(dataSource);
        return repo;
    }
}
