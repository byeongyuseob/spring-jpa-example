package com.example.demo;

import com.example.demo.dto.PersonCreationRequest;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("PersonServiceTest")
public class PersonServiceTest {

    @Autowired private PersonService personService;

    @Test
    @Order(1)
    @DisplayName("Person save...")
    void save(){
        PersonCreationRequest person = PersonCreationRequest
                .builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build();

        personService.createPerson(person);

        assertThat(person.getFirstName()).isEqualTo("gyuseob");
    }

    @Test
    @Order(2)
    @DisplayName("Person findById...")
    void findById(){
        Person person = personService.getPerson(1L);

        assertThat(person.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @DisplayName("Person findAll...")
    void findAll(){
        List<Person> people = personService.getPeople();

        assertNotNull(people);
    }

    @Test
    @Order(4)
    @DisplayName("Person modify...")
    void modify(){
        PersonCreationRequest person = PersonCreationRequest
                .builder()
                .firstName("woo")
                .lastName("ha")
                .build();

        Person suspect = personService.updatePerson(1L, person);

        assertThat(suspect.getLastName()).isEqualTo("ha");
    }

    @Test
    @Order(5)
    @DisplayName("Person delete...")
    void delete(){
        Optional<Person> person = Optional.ofNullable(personService.getPerson(1L));

        person.ifPresent(suspect->{
            personService.deletePerson(suspect.getId());
        });
    }
}
