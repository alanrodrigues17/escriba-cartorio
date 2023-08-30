package br.com.escriba.cartorio.resources.exception.types;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorMessage {

    private int status;
    private String msg;

 
}