package com.example.pruebas.controller;

import com.example.pruebas.DTO.PersonDTO;
import com.example.pruebas.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Devuelve lista de usuarios (copia)
     * @return list DTOPerson
     */
    @GetMapping("/user")
    public List<PersonDTO> getUsers() {
        return personService.getUsers();
    }

    /**
     * Consigue ids de los usuarios.
     * @return lista String
     */
    @GetMapping("/usersId")
    public List<String> getUserIds() {
        return personService.getUsersIds();
    }
}
