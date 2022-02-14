package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.dto.PersonCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Person getPerson(Long id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new EntityNotFoundException("Cant find any person under given ID");
        }
        return person.get();
    }

    public Person createPerson (PersonCreationRequest personCreationRequest) {
        Person person = new Person();
        BeanUtils.copyProperties(personCreationRequest, person);
        return personRepository.save(person);
    }

    public Person updatePerson (Long id, PersonCreationRequest personCreationRequest) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("Person is not existed in the database");
        }

        Person person = optionalPerson.get();

        person.setLastName(personCreationRequest.getLastName());
        person.setFirstName(personCreationRequest.getFirstName());

        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()){
            throw new EntityNotFoundException("Person is not existed in the database");
        }

        personRepository.deleteById(id);
    }

}
