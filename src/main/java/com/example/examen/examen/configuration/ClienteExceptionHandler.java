package com.example.examen.examen.configuration;

import com.example.examen.examen.Exceptions.ClienteExistenteException;
import com.example.examen.examen.Exceptions.ClienteInexistenteException;
import com.example.examen.examen.Exceptions.ClienteVacioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClienteExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ClienteInexistenteException.class })
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Cliente no encontrado",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ ClienteVacioException.class })
    protected ResponseEntity<Object> handleEmpty(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "No puedes dejar campos vacios",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ ClienteExistenteException.class })
    protected ResponseEntity<Object> handleExists(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "El cliente ya Existe",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}


