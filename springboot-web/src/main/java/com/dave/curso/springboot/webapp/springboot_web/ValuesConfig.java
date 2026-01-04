package com.dave.curso.springboot.webapp.springboot_web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration //clase de configuracion, para no tener que hacerlo en la clase principal, debe ser hermana de la clase principal
@PropertySources({
	@PropertySource(value = "classpath:values.properties", encoding = "UTF-8") //carga un archivo de propiedades adicional al application.properties
})
public class ValuesConfig {

}
//Si se pone value = "classpath:values.properties", encoding = "UTF-8" no da error con los acentos y,
//se guarda la configuración del otro properties 
//Si se guarda en donde dice UTF-8 abajo a la izquierda y, se pone ISO 8859-1, 
// se guardan los acentos correctamente