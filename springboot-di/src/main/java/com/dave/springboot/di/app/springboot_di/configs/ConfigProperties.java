package com.dave.springboot.di.app.springboot_di.configs;

import com.dave.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value= "classpath:config.properties", encoding = "UTF-8")
public class ConfigProperties {

    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean("productJson")
    //@Primary si se quiere usar está, se inyecta el resource a la repo para no tener que usar la forma comentada.
    public ProductRepositoryJson productRepositoryJson(){
        return new ProductRepositoryJson(resource);
    }
}
