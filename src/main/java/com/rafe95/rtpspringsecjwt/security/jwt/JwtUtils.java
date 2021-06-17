package com.rafe95.rtpspringsecjwt.security.jwt;

import com.auth0.jwt.JWT;

import javax.servlet.http.HttpServletRequest;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtUtils {

    public static String extractUsername(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        // parse the token and validate it
        return JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
