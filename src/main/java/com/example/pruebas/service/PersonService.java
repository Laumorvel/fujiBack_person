package com.example.pruebas.service;


import com.example.pruebas.DTO.PersonDTO;
import com.example.pruebas.DTO.ProductDTO;
import com.example.pruebas.model.Person;
import com.example.pruebas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private RestTemplate restTemplate;

    //Microservicio de PRODUCT
    static final String URL_BASE = "http://localhost:8000/";

    /**
     * Consigue los usuarios de la bbdd
     *
     * @return copia de los mismos de tipo DTOPerson
     */
    public List<PersonDTO> getUsers() {
        //Consigo los productos guardados en el microservicio de products
        ProductDTO[] products = restTemplate.getForObject(URL_BASE + "product", ProductDTO[].class);
        List<ProductDTO> productsList = new ArrayList<>();
        productsList = Arrays.asList(products);

        //Consigo la lista del repo
        List<Person> people = personRepository.findAll();
        //Creo una lista vacía de tipo DAOPerson que será la que se devuelva
        List<PersonDTO> peopleList = new ArrayList<>();
        //Recorro la lista del repo para añadir a las personas en la nueva lista
        people.forEach(person -> {
            peopleList.add(new PersonDTO(person.getId(), person.getName(), person.getEmail(), person.getDirection(), person.getPhone()));
        });
        //Recorro las listas para añadir el producto a la lista de productos del usuario, en caso de que coincidan en id
        productsList.forEach(product -> {
            addproductToUser(product, peopleList);
        });


        return peopleList;
    }

    /**
     * Modifica la lista de productos de los usuarios.
     * Se le añade el producto que tenga un userId igual al id del usuario
     *
     * @param product
     * @param people
     * @return
     */
    private List<PersonDTO> addproductToUser(ProductDTO product, List<PersonDTO> people) {
        people.forEach(person -> {
            //Se añade esta condición para que, en el caso de que no hubiera usuarios asociados al producto, no salte NullPointerException
            if (product.getUserId() != null && product.getUserId().equals(person.getId())) {
                person.getProducts().add(product);
            }
        });
        return people;
    }


    /**
     * Consigue los ids de los usuarios
     *
     * @return list<String>
     */
    public List<String> getUsersIds() {
        List<PersonDTO> people = getUsers();
        List<String> ids = new ArrayList<>();
        people.forEach(p -> {
            ids.add(p.getId());
        });
        return ids;
    }

}
