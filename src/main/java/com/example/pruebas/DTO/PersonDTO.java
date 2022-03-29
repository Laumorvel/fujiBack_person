package com.example.pruebas.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PersonDTO {

    private String id;
    private String name;
    private String email;
    private String direction;
    private String phone;
    private List<ProductDTO>products = new ArrayList<>();

    public PersonDTO(String id, String name, String email, String direction, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.direction = direction;
        this.phone = phone;
    }

    /**
     * Constructor con lista de productos
     * @param name
     * @param email
     * @param direction
     * @param phone
     * @param products
     */
    public PersonDTO(String name, String email, String direction, String phone, List<ProductDTO> products) {
        this.name = name;
        this.email = email;
        this.direction = direction;
        this.phone = phone;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDTO daoPerson = (PersonDTO) o;

        return id != null ? id.equals(daoPerson.id) : daoPerson.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
