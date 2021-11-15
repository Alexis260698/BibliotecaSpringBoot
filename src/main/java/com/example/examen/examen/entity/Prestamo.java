package com.example.examen.examen.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class Prestamo {
    private LocalDate fecha;
    private ItemPrestamo item;
    private String fechaDevolucion;
    private String estado;

    public Prestamo(LocalDate fecha, ItemPrestamo item, String fechaDevolucion, String estado) {
        this.fecha = fecha;
        this.item = item;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
}
