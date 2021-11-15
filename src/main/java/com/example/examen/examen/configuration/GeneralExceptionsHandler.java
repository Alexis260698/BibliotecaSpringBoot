package com.example.examen.examen.configuration;

import com.example.examen.examen.Exceptions.GeneralsExceptions.ErrorException;
import com.example.examen.examen.Exceptions.GeneralsExceptions.ExcesoDePrestamos;
import com.example.examen.examen.Exceptions.GeneralsExceptions.cantCreateExaption;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ErrorException.class })
    protected ResponseEntity<Object> NoSePudoCrear(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Debes Ingresar el DNI del cliente y el codigo del Item",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ cantCreateExaption.class })
    protected ResponseEntity<Object> noSePudoCrearPrestamo(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "No se pudo crear el prestamo, revisa que no hayas pasado el limite de prestamos",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ ExcesoDePrestamos.class })
    protected ResponseEntity<Object> excesoDePrestamo(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Tienes muchos prestamos pendientes, primero regresa alguno de los objetos",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
