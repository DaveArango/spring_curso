package com.dave.curso.springboot.jpa.springbootjparelationship.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_details")
@Getter
@Setter
@NoArgsConstructor
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean premium;
    private Integer points;

    @OneToOne
    private Client client;

    public ClientDetails(boolean premium, Integer points){
        this.premium = premium;
        this.points = points;
    }

    @Override
    public String toString() {
        return id + " | " +
                premium + " | " +
                points;
    }
}
