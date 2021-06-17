package com.rafe95.rtpspringsecjwt.security.jwt;

import com.rafe95.rtpspringsecjwt.repository.PersonaRepository;
import com.rafe95.rtpspringsecjwt.security.PersonaPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final PersonaRepository personaRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, PersonaRepository personaRepository) {
        super(authenticationManager);
        this.personaRepository = personaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        var username = JwtUtils.extractUsername(request);
        // Search in the DB if we find the user by token subject (username)
        // If so, then grab user details and create spring auth token using username, pass, authorities/roles
        if (username != null) {
            var persona = personaRepository.findByUsername(username);
            var principal = new PersonaPrincipal(persona);
            return new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
        }
        return null;
    }

}




