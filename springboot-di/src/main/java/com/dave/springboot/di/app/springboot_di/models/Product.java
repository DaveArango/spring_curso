package com.dave.springboot.di.app.springboot_di.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Cloneable{
    private Long id;
    private String name;
    private Long price;

    @Override
    public String toString() {
        return id + " | " + name + " | " + price;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.id, this.name, this.price);
        }
    }

    
}
