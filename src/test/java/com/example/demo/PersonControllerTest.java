package com.example.demo;

import com.example.demo.controller.PersonController;
import com.example.demo.dto.PersonCreationRequest;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("PersonContollerTest")
public class PersonControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mvc;

    @MockBean private PersonService personService;

    @Test
    @Order(1)
    @DisplayName("Person save...")
    void save() throws Exception{
        given(personService.createPerson(any()))
                .willReturn(Person.builder()
                        .id(1L)
                        .firstName("gyuseob")
                        .lastName("byeon")
                        .build());

        PersonCreationRequest person = PersonCreationRequest
                .builder()
                .firstName("woo")
                .lastName("ha")
                .build();

        final ResultActions actions = mvc.perform(
                post("/api/v1/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)));

        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("gyuseob"))
                .andExpect(jsonPath("lastName").value("byeon"));
    }

    @Test
    @Order(2)
    @DisplayName("Person findById...")
    void findById() throws Exception {
        given(personService.getPerson(any()))
                .willReturn(Person.builder()
                        .id(1L)
                        .firstName("gyuseob")
                        .lastName("byeon")
                        .build());

        final ResultActions actions = mvc.perform(get("/api/v1/person/1"));

        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("gyuseob"))
                .andExpect(jsonPath("lastName").value("byeon"));
    }

    @Test
    @Order(3)
    @DisplayName("Person findAll...")
    void findAll() throws Exception {
        given(personService.getPeople())
                .willReturn(List.of(Person.builder()
                .firstName("gyuseob")
                .lastName("byeon")
                .build()));

        final ResultActions actions = mvc.perform(get("/api/v1/people"));

        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("gyuseob")));
    }

    @Test
    @Order(4)
    @DisplayName("Person modify...")
    void modify() throws Exception {
        given(personService.updatePerson(any(), any()))
                .willReturn(Person.builder()
                        .id(1L)
                        .firstName("woo")
                        .lastName("ha")
                        .build());

        PersonCreationRequest person = PersonCreationRequest
                .builder()
                .firstName("woo")
                .lastName("ha")
                .build();

        final ResultActions actions = mvc.perform(patch("/api/v1/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)));

        actions
                .andDo(print())
                .andExpect(status().isOk());

        verify(personService).updatePerson(eq(1L), eq(person));
    }

    @Test
    @Order(5)
    @DisplayName("Person delete...")
    void remove() throws Exception {
        final ResultActions actions = mvc.perform(delete("/api/v1/person/1"));

        actions
                .andDo(print())
                .andExpect(status().isOk());

        verify(personService).deletePerson(eq(1L));
    }
}
