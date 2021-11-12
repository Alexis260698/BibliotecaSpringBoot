package com.example.examen.examen.controller;

import com.example.examen.examen.Exceptions.ClienteInexistenteException;
import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.entity.Prestamo;
import com.example.examen.examen.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/prestamos/{dni}")
    public List<Prestamo> getClientes(@PathVariable("dni") String dni) {
        return clienteRepository.getListaPrestamos(dni);
    }

}




