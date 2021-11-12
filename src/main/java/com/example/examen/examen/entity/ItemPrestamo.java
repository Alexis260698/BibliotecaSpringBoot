package com.example.examen.examen.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPrestamo {
    private Integer codigo;
    private String nombre;

    public ItemPrestamo(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
