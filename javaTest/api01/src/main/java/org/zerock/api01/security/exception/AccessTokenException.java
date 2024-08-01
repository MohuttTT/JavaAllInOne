package org.zerock.api01.security.exception;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class AccessTokenException extends RuntimeException {

    // 열거형 타입 멤버 변수 선언
    TOKEN_ERROR token_error;

    public enum TOKEN_ERROR {
        // 토큰과 관련관 다양한 오류 유형 정의
        UNACCEPT(401, "Token is null or too short"),
        BADTYPE(401, "Token type Bearer"),
        MALFORM(403, "Malformed Token"),
        BADSIGN(403, "BadSignatured Token"),
        EXPIRED(403, "Expired Token");

        // 각 오류의 유형은 HTTP 상태 코드(status)와 오류 메시지(msg)를 가짐
        private int status;
        private String msg;

        // 생성자를 통해 status와 msg를 초기화
        TOKEN_ERROR(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        // 각각의 값을 반환하는 getter 메서드를 제공
        public int getStatus() {
            return this.status;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    // 생성자 - TOKKEN_ERROR 타입의 인자를 받은
    public AccessTokenException(TOKEN_ERROR error) {
        // 부모 생성자에 오류 이름 전달
        super(error.name());
        // 멤버 변수 token_error을 초기화
        this.token_error = error;
    }

    // 오류 응답 전송 메서드
    public void sendResponseError(HttpServletResponse response) {

        response.setStatus(token_error.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Gson 라이브러리 사용
        Gson gson = new Gson();

        // JSON 형식의 오류 메시지 생성
        String responseStr = gson.toJson(Map.of(
                "msg", token_error.getMsg(),
                "time", new Date()));

        try {
            response.getWriter().println(responseStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
