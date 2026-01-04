package com.dave.curso.springboot.app.springbootcrud.controllers;

import com.dave.curso.springboot.app.springbootcrud.entities.Product;
import com.dave.curso.springboot.app.springbootcrud.services.ProductService;
import com.dave.curso.springboot.app.springbootcrud.validations.ProductValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // @Autowired
    // private ProductValidation validation;

    @GetMapping
    public List<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //El result siempre se tiene que poner a la derecha del objeto que se quiere validar
    public ResponseEntity<?> create(@Valid @RequestBody Product product,
                                          BindingResult result){
        //validation.validate(product, result); este con el ProductValidation personalizado
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}") // @Valid se usa para validar que el cuerpo de la request no este vacio
    public ResponseEntity<?> update(@PathVariable Long id,
                                          BindingResult result,
                                          @Valid @RequestBody Product product){
        //validation.validate(product, result); este con el ProductValidation
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Product> productOptional = productService.update(id, product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> productOptional = productService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //Para manejar los errores personalizados
    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
