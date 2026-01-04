package com.dave.curso.springboot.di.factura.springboot_difactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Client {
    @Value("${client.name}")
    private String name;
    @Value("${client.lastname}")
    private String lastname;
}
