package com.arthur.schedulingApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ErrorResponse handleResourceNotFoundException(UserNotFoundException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.NOT_FOUND , ex.getMessage());
        error.getBody().setTitle("Usuario nao encontrado");
        return error;
    }


    @ExceptionHandler(ServiceNotFoundException.class)
    public final ErrorResponse handleResourceNotFoundException(ServiceNotFoundException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.NOT_FOUND , ex.getMessage());
        error.getBody().setTitle("Servico nao encontrado");
        return error;
    }

}
