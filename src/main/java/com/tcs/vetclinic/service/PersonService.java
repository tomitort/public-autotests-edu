package com.tcs.vetclinic.service;

import com.tcs.vetclinic.dao.PersonRepository;
import com.tcs.vetclinic.domain.person.Person;
import com.tcs.vetclinic.domain.sort.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person save(Person person) throws IllegalArgumentException {
        if (Objects.isNull(person)) {
            throw new IllegalArgumentException("Person не должен быть пустым");
        }
        return personRepository.save(new Person(person.getName()));
    }

    public List<Person> findAll(int page, int size, SortType sort) throws RuntimeException {
        Pageable paging = PageRequest.of(page, size);
        int limit = paging.getPageSize();
        int offset = paging.getPageNumber() * paging.getPageSize();
        if (SortType.DESC.equals(sort)) {
            return personRepository.findAllDesc(limit, offset);
        }

        return personRepository.findAllAsc(limit, offset);
    }

    public Person findById(Long id) throws PersonNotFoundError {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundError("В репозитории нет клиента с таким id");
        }

        return optionalPerson.get();
    }

    public void deleteById(Long id) throws PersonNotExistError {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotExistError("В репозитории нет клиента с таким id");
        }

        personRepository.deleteById(id);
    }

    public void updateById(long id, Person personDto) throws PersonNotFoundError {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundError("В репозитории нет клиента с таким id");
        }

        personRepository.updateById(id, personDto);
    }
}
