package com.rafe95.rtpspringsecjwt.repository;

import com.rafe95.rtpspringsecjwt.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {
    Persona findByUsername(String username);
}
