package com.rafe95.rtpspringsecjwt.security;

import com.rafe95.rtpspringsecjwt.repository.PersonaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonaPrincipalDetailsService implements UserDetailsService {

    private final PersonaRepository personaRepository;

    public PersonaPrincipalDetailsService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var persona = personaRepository.findByUsername(username);
        return new PersonaPrincipal(persona);
    }
}
