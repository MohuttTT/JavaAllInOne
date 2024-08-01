package org.zerock.api01.util;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// @Controller, @Service, @Repository, @Configure = @Component
// @Component 클래스 레벨 수준
// @Bean 메서드 레벨 수준
@Component
@Log4j2
public class JWTUtil {

    private SecretKey secretKey;

    // 비밀 키 생성 메서드
    public JWTUtil(@Value("${org.zerock.jwt.secret}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String generateToken(Map<String, Object> valueMap, int days) {

        log.info("generateKey..." + secretKey);

        // 헤더 부분
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        // payload 부분 설정
        Map<String, Object> payloads = new HashMap<>(valueMap);

        // 테스트 시에는 짧은 유효 기간
        int time = (60 * 24) * days;  // 테스트는 분단위로 나중에 60*24 (일)단위변경

        // String jwtStr = Jwts

        return Jwts.builder()
                .header()
                .add(headers)
                .and()
                .claims(payloads)
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                // plusDays(일자)
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
                .signWith(secretKey)
                .compact();

    }

    public Jws<Claims> validateToken(String token) throws JwtException {

        Map<String, Object> claim = null;

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }
}
