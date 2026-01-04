package com.dave.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable //Se usa para reutilizar codigo, se usa embeddable en la clase que se pone el codigo que se reutiliza
@Getter
@Setter
public class Audit {
    @Column(name = "create_at")
    private LocalDateTime creatAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Evento del ciclo de vida el entity pre-persist");
        this.creatAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Evento del ciclo de vida al entity pre-update");
        this.updateAt = LocalDateTime.now();
    }

}
