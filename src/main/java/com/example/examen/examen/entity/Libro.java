package com.example.examen.examen.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Libro extends ItemPrestamo {


    public Libro(Integer codigo, String nombre) {
        super(codigo, nombre);
    }


}
