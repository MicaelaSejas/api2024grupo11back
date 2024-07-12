package com.uade.tpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El usuario no existe")

public class UsuarioNotFoundException extends Exception {

}