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
    public void createToken() {
        String token = jwtUtil.createToken(1004L, "John", null);

        Assertions.assertThat(token).contains("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.U-37Hpq5jkrn2_zcsmC9YQR5UdhXKU6124UojSFX9HI");
    }

    @Test
    public void getCalims() {
        String token = "A";

        Claims claims = jwtUtil.getClaims(token);

        Assertions.assertThat(claims.get("userId", Long.class)).isEqualTo(1004L);
        Assertions.assertThat(claims.get("name")).isEqualTo("John");
    }

}