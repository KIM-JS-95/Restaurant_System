package com.reservationsystem.utils;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class JwtUtilTest{

    @Test
    public void createToken(){
        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }

}