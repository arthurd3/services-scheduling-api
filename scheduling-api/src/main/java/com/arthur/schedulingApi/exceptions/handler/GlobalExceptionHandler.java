package com.arthur.schedulingApi.exceptions.handler;

import com.arthur.schedulingApi.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(UserNotAuthenticatedException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Usuario Nao autenticado");
        return error;
    }

    @ExceptionHandler(InvalidRatingException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(InvalidRatingException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Erro ao Avaliar");
        return error;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(UnauthorizedException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Usuario nao autorizado");
        return error;
    }

    @ExceptionHandler(SchedulingNotAvailableException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(SchedulingNotAvailableException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Agendamento nao Disponivel");
        return error;
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public final ErrorResponse handleResourceAlreadyExistsException(NameAlreadyExistsException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Nome ja esta em uso");
        return error;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            PhoneAlreadyExistsException.class
    })
    public final ErrorResponse handleConflictExceptions(RuntimeException ex) {
        var error = ErrorResponse.create(ex , HttpStatus.CONFLICT , ex.getMessage());
        error.getBody().setTitle("Conflito de Dados");
        return error;
    }
}
