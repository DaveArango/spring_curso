package com.dave.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.web.bind.annotation.RestController;

import models.User;
import models.dto.UserDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDto details(){
        UserDto userDto = new UserDto();
        User user = User.builder()
                        .name("David")
                        .lastname("Arango")
                        .build();
        userDto.setTitle("Hola mundo Spring Boot");
        userDto.setUser(user);
        return userDto;
    }
    
    @GetMapping("/list")
    public List<User> list(){
        User user1 = User.builder()
                        .name("Andres")
                        .lastname("Guzman")
                        .build();
        User user2 = User.builder()
                        .name("Carlos")
                        .lastname("Ramirez")
                        .build();
        User user3 = User.builder()
                        .name("Daniel")
                        .lastname("Lopez")
                        .build();
        List<User> users = Arrays.asList(user1, user2, user3);
        return users;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap(){
        User user = User.builder()
                        .name("David")
                        .lastname("Arango")
                        .build();
        Map<String, Object> body = new HashMap<>(); //JSON
        body.put("title", "Hola mundo Spring Boot");
        body.put("user", user);
        return body;
    }

}
