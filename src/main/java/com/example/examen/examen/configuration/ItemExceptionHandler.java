package com.example.examen.examen.configuration;


import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteInexistenteException;
import com.example.examen.examen.Exceptions.ItemExceptions.ItemExistenteException;
import com.example.examen.examen.Exceptions.ItemExceptions.ItemInexistenteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ItemExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ItemInexistenteException.class })
    protected ResponseEntity<Object> itemInexistente(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Item no encontrado",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ ItemExistenteException.class })
    protected ResponseEntity<Object> itemExistente(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "El item ya existe",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
