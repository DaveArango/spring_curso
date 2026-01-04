package com.dave.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.dave.springboot.di.app.springboot_di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@SessionScope
@Primary
@Repository("productList")
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> data;

    public ProductRepositoryImpl(){
        this.data = List.of(
            Product.builder().id(1L).name("Laptop").price(80000L).build(),
            Product.builder().id(2L).name("Mouse").price(1500L).build(),
            Product.builder().id(3L).name("Keyboard").price(3000L).build(),
            Product.builder().id(4L).name("Monitor").price(25000L).build()
        );
    }

    @Override
    public List<Product> findAll(){
        return data;
    }

    @Override
    public Product findById(Long id){
        return data.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

}
