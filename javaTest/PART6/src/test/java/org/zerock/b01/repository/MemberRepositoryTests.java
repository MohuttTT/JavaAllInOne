package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email" + i + "@aaa.bbb")
                    .build();

            member.addRole(MemberRole.USER);

            // 90번 이상 회원에게 ADMIN 역할 추가로 부여
            if(i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {

        Optional<Member> result = memberRepository.getWithRoles("member100");

        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        // enum 타입에 사용할 수 있는 메서드
        // 상수에 대한 이름을 문자열로 가져올 수 있는 -name()
        // 상수의 순서를 나타내는 숫자 값을 반환하는 -ordinal() -- 해당 enum 상수가 선언된 순서를 0부터 시작하는 정수로 반환
        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }

    @Commit
    @Test
    public void testUpdate() {

        String mid = "zolloz2007@gmail.com";    // 소셜로그인으로 추가된 사용자로 현재 DB에 존재하는 이메일 사용해야 함
        String mpw = passwordEncoder.encode("54321");

        memberRepository.updatePassword(mpw, mid);
    }
}