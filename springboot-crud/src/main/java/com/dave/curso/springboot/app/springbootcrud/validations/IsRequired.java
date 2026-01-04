package com.dave.curso.springboot.app.springbootcrud.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequiredValidation.class) //Se enlaza con la validacion creada
@Retention(RetentionPolicy.RUNTIME) //se esta creando una anotación para validar
@Target({ElementType.FIELD, ElementType.METHOD}) // se define el alcance del metodo
public @interface IsRequired {
    String message() default "Es requerido usando anotaciones";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
