package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.repository.MemberRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    // 회원가입 시 회원 비밀번호 암호화 처리
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {

        String mid = memberJoinDTO.getMid();

        // repository를 이용하여 해당 회원이 DB에 존재하는 회원인지 확인
        boolean exist = memberRepository.existsById(mid);

        // 존재하는 회원이라면 회원가입 X -> MidExistException 예외 발생
        if (exist) {
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("==================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
    }
}
