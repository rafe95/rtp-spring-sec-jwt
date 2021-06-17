package com.rafe95.rtpspringsecjwt.service;

import com.rafe95.rtpspringsecjwt.model.Persona;
import com.rafe95.rtpspringsecjwt.repository.PersonaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonaService(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Persona save(Persona persona) {
        persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        return personaRepository.save(persona);
    }

    public Persona getById(String id) {
        return personaRepository.findById(id).get();
    }

    public List<?> getAll() {
        return personaRepository.findAll();
    }

    public Persona update(String id, Persona persona) {
        persona.setId(id);
        return personaRepository.save(persona);
    }

    public void deleteById(String id) {
        personaRepository.deleteById(id);
    }

    public Persona getUserByUsername(String username) {
        return personaRepository.findByUsername(username);
    }


}
