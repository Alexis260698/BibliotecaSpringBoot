package com.example.examen.examen.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Sistema {
    private List<Prestamo> prestamos;
    private List<Cliente> clientes;
    private List<ItemPrestamo> items;



}
