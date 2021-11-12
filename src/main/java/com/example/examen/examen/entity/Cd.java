package com.example.examen.examen.entity;

public class Cd extends ItemPrestamo{

    public Integer getDiasDevolucion(){
        return 0;
    }

    public Cd( Integer codigo,String nombre) {
    super(codigo, nombre);
    }
}
