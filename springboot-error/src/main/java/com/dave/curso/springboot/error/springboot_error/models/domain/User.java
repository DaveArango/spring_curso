package com.dave.curso.springboot.error.springboot_error.models.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastname;

    private Role role;

    public User(Long id, String name, String lastname){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    //public String getRoleName(){
    //    return role.getName();
    //}
}
