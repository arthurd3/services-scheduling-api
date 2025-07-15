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

    @ExceptionHandler(SchedulingNotFoundException.class)
    public final ErrorResponse handleResourceNotFoundException(SchedulingNotFoundException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.NOT_FOUND , ex.getMessage());
        error.getBody().setTitle("Agendamento nao encontrado");
        return error;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(EmailAlreadyExistsException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("E-mail ja utilizado");
        return error;
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(PhoneAlreadyExistsException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Telefone ja utilizado");
        return error;
    }


}
