package com.example.demo;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@DisplayName("PersonRepositoryTest")
class PersonRepositoryTest {
    @Autowired private PersonRepository personRepository;

    @Test
    @Order(1)
    @DisplayName("Person save...")
    void save(){
        final Person savedPerson = personRepository.save(Person.builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build());

        assertEquals("byeon", savedPerson.getLastName());
        assertEquals("gyuseob", savedPerson.getFirstName());
    }

    @Test
    @Order(2)
    @DisplayName("Person findById...")
    void findById(){
        final Person savedPerson = personRepository.save(Person.builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build());

        final Optional<Person> suspect = personRepository.findById(savedPerson.getId());

        suspect.ifPresent(suspectPerson -> {
            assertEquals(savedPerson.getId(), suspect.get().getId());
            assertEquals(savedPerson.getFirstName(), suspect.get().getFirstName());
            assertEquals(savedPerson.getLastName(), suspect.get().getLastName());
        });
    }

    @Test
    @Order(3)
    @DisplayName("Person findAll...")
    void findAll(){
        final Optional<List<Person>> people = Optional.of(personRepository.findAll());
        people.ifPresent(System.out::println);
    }

    @Test
    @Order(4)
    @DisplayName("Person modify...")
    void modify(){
        final Person savedPerson = personRepository.save(Person.builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build());

        final Optional<Person> suspect = personRepository.findById(savedPerson.getId());

        suspect.ifPresent(suspectPerson ->{
            suspectPerson.setFirstName("dow");
            suspectPerson.setLastName("pow");

            final Person modifiedPerson = personRepository.save(suspectPerson);
            final Optional<Person> modifiedSuspect = personRepository.findById(modifiedPerson.getId());

            modifiedSuspect.ifPresent(t -> {
                assertEquals(modifiedPerson.getId(), modifiedSuspect.get().getId());
                assertEquals(modifiedPerson.getFirstName(), modifiedSuspect.get().getFirstName());
                assertEquals(modifiedPerson.getLastName(), modifiedSuspect.get().getLastName());
            });
        });
    }

    @Test
    @Order(5)
    @DisplayName("Person delete...")
    void delete(){
        final Person savedPerson = personRepository.save(Person.builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build());

        final Optional<Person> suspect = personRepository.findById(savedPerson.getId());

        personRepository.deleteById(suspect.get().getId());
    }
}
