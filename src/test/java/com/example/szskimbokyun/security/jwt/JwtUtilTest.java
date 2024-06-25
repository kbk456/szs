package com.example.szskimbokyun.security.jwt;

import com.example.szskimbokyun.service.dto.CustomUserInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private String secretKey = "mySecretKey123456789012345678901234567890mySecretKey123456789012345678901234567890"; // Example secret key, should be base64 encoded
    private long expirationTime = 3600L; // 1 hour expiration

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secretKey, expirationTime);
    }

    @Test
    void testCreateAccessToken() {
        CustomUserInfoDto member = new CustomUserInfoDto();
        member.setUserId("1");
        member.setRegNo("921108-1582816");
        member.setName("동탁");

        String token = jwtUtil.createAccessToken(member);
        System.out.println(token);
        assertNotNull(token);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtUtil.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("1", claims.get("userId").toString());
        assertEquals("921108-1582816", claims.get("regNo"));
        assertEquals("동탁", claims.get("name"));
    }

    @Test
    void testValidateToken() {
        CustomUserInfoDto member = new CustomUserInfoDto();
        member.setUserId("1");
        member.setRegNo("921108-1582816");
        member.setName("John Doe");

        String token = jwtUtil.createAccessToken(member);
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void testGetUserId() {
        CustomUserInfoDto member = new CustomUserInfoDto();
        member.setUserId("1");
        member.setRegNo("921108-1582816");
        member.setName("John Doe");

        String token = jwtUtil.createAccessToken(member);
        Long userId = jwtUtil.getUserId(token);
        assertEquals("1", userId);
    }
}
