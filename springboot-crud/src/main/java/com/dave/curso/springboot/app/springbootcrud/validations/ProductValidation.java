package com.dave.curso.springboot.app.springbootcrud.validations;

import com.dave.curso.springboot.app.springbootcrud.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component //Una alternativa a las notaciones que se encuentran en la clase entidad usando @NotNull y llamando
//De forma personalizada a un mensage creado en properties
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "no puede ser vacio.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.product.description");

        if(product.getDescription() == null || product.getDescription().isBlank()){
            errors.rejectValue("description", null, "no puede estar sin nada.");
        }

        if(product.getPrice() == null){
            errors.rejectValue("price", null, "no puede ser nulo.");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "requiere un minimo.");
        }
    }
}
