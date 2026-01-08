package com.dave.curso.springboot.app.springbootcrud.entities;

import com.dave.curso.springboot.app.springbootcrud.validations.ExistsByUsername;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExistsByUsername
    @NotBlank
    @Size(min = 4, max = 12)
    @Column(unique = true)
    private String username;

    // @JsonIgnore // NO se puede ya que cuando se intenta hacer un POST dice que se necesita
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Para darle el acceso solo cuando se escribe, no cuando se devuelve en un response, así se oculta
    private String password;

    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"}) //Para ignorar cierto atributos de este objeto, sino se vuelve un loop infinito
    @ManyToMany
    @JoinTable(
            name = "users_roles", //tabla intermedia
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;

    private boolean enabled;

    @Transient //Se especifica que es un campo que no es de persistencia (de la tabla de la dB)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void prePersist(){
        enabled = true;
    }

    public User() {
        this.roles = new ArrayList<>();
    }

    public User(String username,
                String password,
                List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return username + " | " + password + " | " + roles;
    }
}
