package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.dto.PersonCreationRequest;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonCreationRequest personCreationRequest) {
        return ResponseEntity.ok(personService.createPerson(personCreationRequest));
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@Valid @RequestBody PersonCreationRequest personCreationRequest) {
        return ResponseEntity.ok(personService.updatePerson(id, personCreationRequest));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }

}
