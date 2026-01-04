package com.dave.curso.springboot.error.springboot_error.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String message;
    private String error;
    private int status;
    private Date date;
}
