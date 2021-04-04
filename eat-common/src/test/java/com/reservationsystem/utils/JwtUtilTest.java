package com.reservationsystem.utils;

import io.jsonwebtoken.Claims;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class JwtUtilTest{

    private static final String SECRET = "12345678901234567890123456789012";
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken(){
        String token = jwtUtil.createToken(1004L, "John");
        assertThat(token, containsString("."));
    }

    @Test
    public void getCalims() {
        String token = "eyJhbGciOiJIUzI1NiJ9." +
                "eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0." +
                "8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";

        Claims claims = jwtUtil.getClaims(token);
        Assertions.assertThat(claims.get("userId", Long.class)).isEqualTo(1004L);
        Assertions.assertThat(claims.get("name")).isEqualTo("John");
    }

}