package com.example.examen.examen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Cliente {
    private String nombre;
    private String dni;
    private String domicilio;
    private List<Prestamo> prestamos= new ArrayList<>();

    public Cliente(String nombre, String dni, String domicilio) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
    }

}
