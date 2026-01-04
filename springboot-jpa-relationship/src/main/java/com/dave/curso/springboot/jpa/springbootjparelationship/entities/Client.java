package com.dave.curso.springboot.jpa.springbootjparelationship.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    // @JoinColumn(name = "client_id") //En la tabla de Address, se crea esta columna (llave foranea)
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable( //Se usa para normalizar creando la tabla intermedia, se asocian las llaves.
            name = "tbl_clientes_to_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "client") //Se especifica la relación inversa, el nombre del atributo en la otra clase
    private List<Invoice> invoices;

    public Client(String name,
                  String lastname) {
        this.name = name;
        this.lastname = lastname;
        addresses = new ArrayList<>();
        invoices = new ArrayList<>();
    }

    public Client addInvoice(Invoice invoice){
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }

    public void removeInvoce(Invoice invoice){
        this.getInvoices().remove(invoice);
        invoice.setClient(null);
    }

    @Override
    public String toString() {
        return id + " | " +
                name + " | " +
                lastname + " | " +
                addresses + " | " +
                invoices.toString();
    }
}
