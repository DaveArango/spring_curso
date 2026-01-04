package com.dave.curso.springboot.app.aop.springbootaop.services;

public interface GreetingService {
    //Realmenete puede devolver cualquier cosa
    String sayHello(String person, String phrase);
    String sayHelloError(String person, String phrase);
}
