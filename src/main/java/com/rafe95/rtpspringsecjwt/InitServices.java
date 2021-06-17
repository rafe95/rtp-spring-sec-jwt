package com.rafe95.rtpspringsecjwt;

import com.rafe95.rtpspringsecjwt.model.Persona;
import com.rafe95.rtpspringsecjwt.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitServices implements CommandLineRunner {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public InitServices(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        var all = personaRepository.findAll();
        boolean noAdmin = all.stream().noneMatch(persona ->
                persona.getRoles().contains("ADMIN")
        );
        if (noAdmin) {
            System.out.println("Created a default super-admin");
            var password = passwordEncoder.encode("apiAdministrationPassw0rd");
            var email = "admin@mail.com";
            var rootAdmin = new Persona(
                    "root", password, email
                    , "ADMIN", "ALL");
            personaRepository.save(rootAdmin);
        }
    }

}
