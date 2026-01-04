package com.dave.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import models.dto.ParamDto;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "No message provided", name="message") String message) { // el name es opcional, sirve para pasar de con otro nombre el parametro
        return ParamDto.builder()
                .message(message) // (message != null) ? message : "No message provided"
                .build();
    }

    @GetMapping("/bar") // el request param es con el ? y separando con &
    public ParamDto bar(@RequestParam() String text, @RequestParam Integer code){
        return ParamDto.builder()
                .message(text)
                .code(code)
                .build();
    }

    @GetMapping("/request")
    public ParamDto request(HttpServletRequest request){ // Forma de hacerlo con servlet (nativa)
        ParamDto params = new ParamDto();
        params.setCode(Integer.parseInt(request.getParameter("code")));
        params.setMessage(request.getParameter("message"));
        return params;
    }
}
