package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

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
}
