package com.dave.curso.springboot.di.factura.springboot_difactura.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Product product;
    private Integer quantity;

    public int getImporte(){
        return quantity * product.getPrice();
    }
}
