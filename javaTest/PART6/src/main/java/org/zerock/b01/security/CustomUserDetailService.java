package org.zerock.b01.security;

import groovy.transform.Final;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.repository.MemberRepository;
import org.zerock.b01.security.dto.MemberSecurityDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*private PasswordEncoder passwordEncoder;

    public CustomUserDetailService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }*/

    // username으로 사용자 인증 -- 사용자 정보 로딩
    /*
    ** Security에서는 사용자 ID를 "username"이라고 한다.
        1. username으로 사용자 인증 후 사용자 정보 로딩 -- id 인증 먼저 > loadUserByUsername(String username)
        2. 로딩한 정보의 패스워드로 사용자 패스워드와 검증 -- id에 맞는 패스워드 검증
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: " + username);

        // DB에서 해당하는 username의 값이 있는지 확인
        // getWithRoles() -- 해당하는 회원과 회원 권한을 DB에서 조회해 오는 메서드
        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()) {  // 해당하는 id의 사용자가 없는 경우라면,
            throw new UsernameNotFoundException("username not found...");
        }

        // 해당 회원이 있는 경우라면
        Member member = result.get();   // -- 엔티티

        // 사용자 정보 반환(엔티티 자체 X -> 엔티티를 DTO로 변환한 UserDetails 객체를 반환)

        // 엔티티 -> DTO로 변환 (DTO는 security에서 사용자 정보를 인증할 수 있는 UserDetails 구현 객체로 만들어야 한다)
        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getMid(),
                        member.getMpw(),
                        member.getEmail(),
                        member.isDel(),
                        false,  // 소셜 로그인 회원이 아님을 치크
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info("memberSecurityDTO");
        log.info(memberSecurityDTO);

        // UserDetails interface -> 구현체로 User 클래스 객체 생성
        /*UserDetails userDetails = User.builder()
                .username("user1")  // 사용자 id(username)
                .password(passwordEncoder.encode("1111"))   // 패스워드
                .authorities("ROLE_USER")   // 인가(권한)
                .build();*/

        return memberSecurityDTO; // 사용자 정보(패스워드 검증, 인가(권한) 등등)
    }
}
