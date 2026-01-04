package com.dave.curso.springboot.di.factura.springboot_difactura.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private Integer price;
}
