package com.dave.curso.springboot.jpa.springbootjparelationship.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    //Para hacer una relación bi-direccional
    @JoinColumn(name = "id_client_temp") //El dueño de la relación es en donde va la foreign key, la columna
    @ManyToOne //El primero que aparece es el de la clase en la que estoy
    private Client client;

    public Invoice(String description,
                   Long total) {
        this.description = description;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id + " | " +
                description + " | " +
                total + " | ";
    }
}
