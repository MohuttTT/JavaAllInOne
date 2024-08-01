package org.zerock.b01.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// Spring Security 접근 거부(403 Forbidden) 상황을 처리하는 사용자 정의 핸들러 클래스 구현
@Log4j2
public class Custom403Handler implements AccessDeniedHandler {
    // AccessDeniedHandler 인터페이스를 구현함으로서, 접근 거부 처리 로직을 사용자 정의 핸들러로 사용

    // AccessDenied 발생 -> handel() 호출(접근 거부 상황이 발생했을 때 호출됨)
    // HttpServletRequest : 클라이언트 요청 정보
    // HttpServletResponse : 클라이언트에게 내보낼 응답 정보
    // AccessDeniedException : 접근 거부 상황에 대한 예외 정보
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException , ServletException {

        log.info("---------ACCESS DENIED-------");

        response.setStatus(HttpStatus.FORBIDDEN.value());   // HTTP 응답 상태를 403 Forbidden으로 설정

        // JSON 요청이었는지 확인
        String contentType = request.getHeader("Content-Type");
        // String contentType = request.getContentType();

        boolean jsonRequest = contentType.startsWith("application/json");
        // boolean jsonRequest = contentType.contains("application/json");

        log.info("isJSON: " + jsonRequest);

        // 일반 request
        if (!jsonRequest) {

            // 사용자를 로그인 페이지로 리디렉션한다. URL에 `error=ACCESS_DENIED` 쿼리 파라미터를 추가하여 접근 거부 상황을 알린다
            response.sendRedirect("/member/login?error=ACCESS_DENIED");
        }
    }
}
