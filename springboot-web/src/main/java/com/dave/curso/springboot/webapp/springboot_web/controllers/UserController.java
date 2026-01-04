package com.dave.curso.springboot.webapp.springboot_web.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import models.User;

//Con Thymeleaf
@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = User.builder()
                        .name("David")
                        .lastname("Arango")
                        .email("davidarango@gmail.com")
                        .build();
        model.addAttribute("title", "Hola mundo Spring Boot");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        model.addAttribute("title", "Bienvenido, Lista de usuarios");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel(){
        return Arrays.asList(
            new User("Andres", "Perez", "hola@gmail.com"),
            new User("Juan", "Gomez", "juan@gmail.com"),
            new User("Karla", "Ramirez", "karla@gmail.com"),
            new User("Maria", "Lopez", "maria@gmail.com")
        );
    }
}