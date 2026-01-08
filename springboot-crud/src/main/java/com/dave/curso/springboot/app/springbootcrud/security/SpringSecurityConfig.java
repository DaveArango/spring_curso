package com.dave.curso.springboot.app.springbootcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Se usa para encriptar la contrasena
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ // Para cualquier ruta de los usuarios se les da permisos
        return http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // Para que solo se pueda crear con ese metodo de forma publica
                        .anyRequest()
                        .authenticated()) // Si no es de la ruta puesta se requiere autenticacion
                        .csrf(config -> config.disable())
                        .sessionManagement(management -> management
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                            .build();
    }
}
