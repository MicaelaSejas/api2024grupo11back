package com.uade.tpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El username escogido ya existe. Por favor elija otro")

public class UsuarioDuplicatedException extends Exception {

}