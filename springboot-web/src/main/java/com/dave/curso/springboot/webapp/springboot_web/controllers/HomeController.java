package com.dave.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Con Thymeleaf
@Controller
public class HomeController {
    @GetMapping({"", "/", "/home"}) //Cualquier ruta lleva al "home"
    public String home() {
        //El redirect es para redirigir a otra ruta, hace un refresh de la pagina, reinicia la request
        //return "redirect:/details"; //Nombre de la vista por "defecto"

        //El forward es para mantener la misma ruta pero cargar otra vista, no hace refresh, 
        // no reinicia la request
        return "forward:/details"; //Nombre de la vista por "defecto"
    }
}
