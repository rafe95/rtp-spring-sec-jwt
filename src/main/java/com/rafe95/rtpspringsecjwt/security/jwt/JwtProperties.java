package com.rafe95.rtpspringsecjwt.security.jwt;

public class JwtProperties {

    public static final String SECRET = "Don'tTellYourSecret";

    public static final int EXPIRATION_TIME = 36_000_000; // 10 hours

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
