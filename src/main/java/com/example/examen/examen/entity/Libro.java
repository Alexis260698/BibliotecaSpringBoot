package com.example.examen.examen.entity;

public class Libro extends ItemPrestamo{

   public Integer getDiasDevolucion(){
       return 0;
   }

    public Libro( Integer codigo,String nombre) {
        super(codigo, nombre);
    }
}
