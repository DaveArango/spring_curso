package com.dave.curso.springboot.app.springbootcrud.entities;

import com.dave.curso.springboot.app.springbootcrud.validations.IsExistsDB;
import com.dave.curso.springboot.app.springbootcrud.validations.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsExistsDB
    @IsRequired
    private String sku;

    @NotEmpty(message = "{NotEmpty.product.name}") //Para validar que no haya nada, se usa el message para personalizar
    @Size(min = 3, max = 20)
    private String name;

    @Min(value = 500, message = "{Min.product.price}")
    @NotNull(message = "{NotNull.product.price}") //Se usa para objetos como LocalDate, etc.
    private Integer price;

    // @NotBlank(message = "NotBlank.product.description") //Para validar que no sea vacio
    @IsRequired(message = "{IsRequired.product.description}") //usando la validacion creada
    private String description;

    @Override
    public String toString() {
        return id + " | " +
                name + " | " +
                price + " | " +
                description;
    }
}
