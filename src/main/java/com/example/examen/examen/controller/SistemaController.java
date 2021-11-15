package com.example.examen.examen.controller;


import com.example.examen.examen.Exceptions.GeneralsExceptions.ErrorException;
import com.example.examen.examen.Exceptions.GeneralsExceptions.ExcesoDePrestamos;
import com.example.examen.examen.Exceptions.GeneralsExceptions.cantCreateExaption;
import com.example.examen.examen.repository.ClienteRepository;
import com.example.examen.examen.repository.PrestamoRepository;
import com.example.examen.examen.repository.SistemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SistemaController {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemController itemRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Value("${my.enviroment}")
    private String enviroment;

    @Value("${bliblioteca.name}")
    private String nombre;

    @Value("${biblioteca.direccion}")
    private String direccion;

    @GetMapping("/getEnviroment")
    public String enviroment(){
        return enviroment;
    }

    @GetMapping("/getDatos")
    public String getDatos(){
        return nombre+", "+ direccion;
    }

    @PostMapping("/agregarPrestamo")
    public void crearPrestamo(@RequestParam(name="dni") Integer dni, @RequestParam(name="codigo") Integer codigo) throws cantCreateExaption {
        try {
                prestamoRepository.crearPrestamo(dni, codigo);
        }catch (cantCreateExaption | ExcesoDePrestamos e){
            throw new cantCreateExaption();
        }


    }



}
