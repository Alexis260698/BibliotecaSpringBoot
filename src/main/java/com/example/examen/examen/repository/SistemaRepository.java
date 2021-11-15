package com.example.examen.examen.repository;


import com.example.examen.examen.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
public class SistemaRepository {

    private List<Prestamo> listaPrestamos = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<ItemPrestamo> items = new ArrayList<>();





}
