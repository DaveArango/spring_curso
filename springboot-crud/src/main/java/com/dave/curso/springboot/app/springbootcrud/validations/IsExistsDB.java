package com.dave.curso.springboot.app.springbootcrud.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsExistsDBValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDB {
    String message() default "ya existe la base de datos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
