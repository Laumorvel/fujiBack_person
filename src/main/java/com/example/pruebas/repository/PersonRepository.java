package com.example.pruebas.repository;

import com.example.pruebas.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    //public Person findByEmail(String email);

    @Query("SELECT id FROM person")
    public List<String> getIds();

}
