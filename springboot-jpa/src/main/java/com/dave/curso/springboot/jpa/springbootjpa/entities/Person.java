package com.dave.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "persons") // Si no se pone, el nombre de la tabla es el nombre de la clase
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para mapear, de tipo id y auto incremental
    private Long id;

    // Se mapea de forma automatica
    private String name;
    private String lastname;

    @Embedded //Se usa para reutilizar codigo, se usa el embedded en la clase que se quiere usar
    private Audit audit;

    // Se debe mapea por la palabra compuesta
    @Column(name = "programming_language")
    private String programmingLanguage;

    @Override
    public String toString() {
        return id + " | " +
                name + " | " +
                lastname + " | " +
                programmingLanguage +  " | " +
                audit.getCreatAt() + " | " +
                audit.getUpdateAt();
    }
}
