package com.dave.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import models.dto.ParamDto;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    //Con @Value se inyectan configuraciones

    @Value("${config.username}") //Inyecta el valor de application.properties o un valor por defecto
    private String username;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("${config.code}")
    private Integer code;

    // Spring Lenguage Expression SpEL con @Value
    @Value("#{ '${config.listOfValues}'.split(',')}") //Inyecta una lista separada por comas
    private List<String> valueList;

    @Value("#{ '${config.listOfValues}'.toUpperCase()}") //Inyecta una cadena en mayúsculas
    private String valueString;

    @Value("#{${config.valuesMap}}") //Inyecta un mapa desde properties
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.key1}") //Inyecta un valor específico del mapa
    private String valuesString;

    //Con @Autowired se inyecta el entorno de la aplicación, los valores de config que se inyectaron
    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}/{code}") //debe tener el mismo nombre que el PathVariable, se separa con /
    public ParamDto baz(@PathVariable String message, @PathVariable Integer code){
        return ParamDto.builder()
                .message(message)
                .code(code)
                .build();
    }

    @PostMapping("/create") // Pasa un JSON internamente, por eso es en el body
    public User create(@RequestBody User user){
        //lógica de guardado en dB
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values") //se puede inyectar los valores de properties como parámetros
    public Map<String, Object> values(@Value("${config.message}") String message){
        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message"));
        json.put("listOfValues", listOfValues);
        json.put("code", code);
        json.put("code2", environment.getProperty("config.code", Long.class));
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valuesMap", valuesMap);
        json.put("valuesString", valuesString);
        return json;
    }
}