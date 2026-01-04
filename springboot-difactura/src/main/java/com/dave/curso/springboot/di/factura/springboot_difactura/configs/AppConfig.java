package com.dave.curso.springboot.di.factura.springboot_difactura.configs;

import com.dave.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.dave.curso.springboot.di.factura.springboot_difactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice(){
        Product p1 = Product.builder()
                .name("Kit de Mancuernas con barra")
                .price(120000)
                .build();
        Product p2 = Product.builder()
                .name("Barra multiusos")
                .price(300000)
                .build();
        Product p3 = Product.builder()
                .name("Rueda abdominal")
                .price(40000)
                .build();
        Product p4 = Product.builder()
                .name("Colcha de ejercicio")
                .price(35000)
                .build();
        return Arrays.asList(
                new Item(p1, 2),
                new Item(p2, 3),
                new Item(p3, 4),
                new Item(p4, 5)
        );
    }

    @Bean("default")
    List<Item> itemsInvoiceOficina(){
        Product p1 = Product.builder()
                .name("Monitor")
                .price(500000)
                .build();
        Product p2 = Product.builder()
                .name("Teclado")
                .price(50000)
                .build();
        Product p3 = Product.builder()
                .name("Raton")
                .price(20000)
                .build();
        Product p4 = Product.builder()
                .name("CPU")
                .price(250000)
                .build();
        return Arrays.asList(
                new Item(p1, 1),
                new Item(p2, 4),
                new Item(p3, 3),
                new Item(p4, 2)
        );
    }
}
