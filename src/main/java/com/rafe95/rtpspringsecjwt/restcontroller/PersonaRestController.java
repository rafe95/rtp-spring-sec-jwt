package com.rafe95.rtpspringsecjwt.restcontroller;

import com.rafe95.rtpspringsecjwt.model.Persona;
import com.rafe95.rtpspringsecjwt.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PersonaRestController {

    private final PersonaService personaService;

    public PersonaRestController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/api/v1/persona")
    public ResponseEntity<?> create(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.save(persona));
    }

    @GetMapping("/api/v1/persona/{id}")
    public ResponseEntity<?> readOne(@PathVariable String id) {
        return ResponseEntity.ok(personaService.getById(id));
    }

    @GetMapping("/api/v1/personas")
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(personaService.getAll());
    }

    @RequestMapping(value = "/api/v1/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.update(id, persona));
    }

    @DeleteMapping("/api/v1/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        personaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
