package org.zerock.api01.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void testGenerate() {

        Map<String, Object> claimMap = Map.of("mid", "ABCDE");

        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        log.info(jwtStr);
    }

    @Test
    void testValidate() {
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtaWQiOiJBQkNERSIsImlhdCI6MTcyMjMwMzE1MSwiZXhwIjoxNzIyMzg5NTUxfQ.BewAW65KlWbVEFZOnto1Y1TyM5j7Tb0Vlu1P6oVc8cQ";

        Jws<Claims> claim = jwtUtil.validateToken(jwtStr);

        log.info(claim);
    }

    @Test
    public void testAll() {
        String jwtStr = jwtUtil.generateToken(Map.of("mid", "AAAA", "email", "aaaa@bbb.com"),1);

        log.info(jwtStr);

        Jws<Claims> claim = jwtUtil.validateToken(jwtStr);

        log.info("MID : " + claim.getPayload().get("mid"));
        log.info("EMAIL : " + claim.getPayload().get("email"));

    }
}