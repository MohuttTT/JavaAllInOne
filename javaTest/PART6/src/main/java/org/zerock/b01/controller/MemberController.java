package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.service.MemberService;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    // 의존성 주입
    private final MemberService memberService;

    @GetMapping("/login")   // /member/login 요청이 들어오면 loginGET() 실행
    public void loginGET(String error, String logout) {
        /*
            Controller로 동작한느 메서드의 반환값이 void라면,
            해당 요청이 들어온 주소의 page를 반환한다
            ex) /member/login.html
        */
        log.info("login get..............");
        log.info("logout: " + logout);

        if(logout != null) {
            log.info("user logout........");
        }
    }

    /* 회원가입 페이지 */
    @GetMapping("/join")
    public void joinGET() {

        log.info("join get...");
    }

    /* 회원가입 처리 */
    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {

        log.info("join post...");
        log.info(memberJoinDTO);

        try {
            memberService.join(memberJoinDTO);
        } catch (MemberService.MidExistException e) {

            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/member/login";    // 회원 가입 후 로그인
    }
}
