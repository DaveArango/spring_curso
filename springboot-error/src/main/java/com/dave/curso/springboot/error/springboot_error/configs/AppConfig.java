package com.dave.curso.springboot.error.springboot_error.configs;

import com.dave.curso.springboot.error.springboot_error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<User> users() {
        List<User> users= new ArrayList<>();
        users.add(new User(1L, "pepe", "pedro"));
        users.add(new User(2L, "andres", "paco"));
        users.add(new User(3L, "dave", "vivas"));
        users.add(new User(4L, "aleja", "arango"));
        users.add(new User(5L, "maria", "clemencia"));
        return users;
    }
}
