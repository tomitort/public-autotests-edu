package com.tcs.vetclinic.dao;

import com.tcs.vetclinic.domain.person.Person;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Long> {
    @Modifying
    @Query("UPDATE person p SET p.name = :#{#person.name} WHERE p.id = :id")
    void updateById(long id, Person person);

    @Query("""
      SELECT * FROM person
      ORDER BY ID
      LIMIT :limit OFFSET :offset
      """
    )
    List<Person> findAllAsc(int limit, int offset);

    @Query("""
      SELECT * FROM person
      ORDER BY ID DESC
      LIMIT :limit OFFSET :offset
      """
    )
    List<Person> findAllDesc(int limit, int offset);
}
