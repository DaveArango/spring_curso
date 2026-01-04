package com.dave.curso.springboot.jpa.springbootjpa;

import com.dave.curso.springboot.jpa.springbootjpa.entities.Person;
import com.dave.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        list();
        findOne("re");
    }

    private void list() {
        System.out.println("--------------------------------");
        List<Person> persons = repository.findByProgrammingLanguageAndName("Java", "Andres");
        persons.forEach(System.out::println);
        System.out.println("--------------------------------");
        List<Object[]> personValues = repository.obtenerPersonData();
        personValues.forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
    }

    public void findOne(String name){
        System.out.println("--------------------------------");
        //Person person = null;
        //Optional<Person> optionalPerson = repository.findById(id);
        //if (optionalPerson.isPresent()) {
        //    person = optionalPerson.get();
        //}
        //System.out.println(person);
        repository.findOneLikeName(name).ifPresent(System.out::println);
    }
}
