package com.tcs.vetclinic.api;

import com.tcs.vetclinic.domain.person.Person;
import com.tcs.vetclinic.domain.sort.SortType;
import com.tcs.vetclinic.service.PersonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController implements PersonClient {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public Iterable<Person> findAll(int page, int size, SortType sort) {
        return personService.findAll(page, size, sort);
    }

    public Person findById(long id) {
        return personService.findById(id);
    }

    public Long create(Person person) {
        return personService.save(person).getId();
    }

    public void delete(long id) {
        personService.deleteById(id);
    }

    public void updateById(long id, Person personDto) {
        personService.updateById(id, personDto);
    }
}
