package com.dave.curso.springboot.jpa.springbootjparelationship.repositories;

import com.dave.curso.springboot.jpa.springbootjparelationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query("SELECT c FROM Client c JOIN FETCH c.addresses WHERE c.id = ?1")
    Optional<Client> findOne(Long id);
}
