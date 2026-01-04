package com.dave.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dave.springboot.di.app.springboot_di.models.Product;
import com.dave.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    @Value("${config.price.tax}")
    private Double tax;

    private ProductRepository repository;

    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository) {
        this.repository = repository;
    }

    //Logica de negocio: aplicar impuesto del 25% al precio
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            // Se crea un nuevo producto con el precio modificado, 
            // para cumplir con el principio de inmutabilidad
            // Product newProd = new Product(p.getId(), p.getName(), priceImp.longValue());

            // Double priceImp = p.getPrice() * environment.getProperty("config.price.tax", Double.class); //Con el enviroment
            Double priceImp = p.getPrice() * tax; // con el Value
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceImp.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    public Product findById(Long id){
        return repository.findById(id);
    }
}
