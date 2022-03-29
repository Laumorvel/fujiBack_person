package com.example.pruebas;

import com.example.pruebas.model.Person;
import com.example.pruebas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class PruebasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebasApplication.class, args);
	}

	@Bean
	public RestTemplate registerRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	CommandLineRunner initData (PersonRepository personRepo){
		personRepo.deleteAll();
		Person person1 = new Person("Pepe Luis", "pepeluis@gmail.com", "Calle Bonita, 53", "678678767");
		Person person2 = new Person("María Luisa", "marialuisa@gmail.com", "Calle María, 5", "656565656");
		Person person3 = new Person("Pepi", "pepi@gmail.com", "Calle mar, 1", "678338767");


		return(args) -> {
			personRepo.saveAll(Arrays.asList(person1, person2, person3));
		};
	}

}
