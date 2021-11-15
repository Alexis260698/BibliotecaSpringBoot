package com.example.examen.examen.repository;

import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.entity.Libro;
import com.example.examen.examen.entity.Prestamo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class ClienteRepository {

    private List<Prestamo> listaPrestamos = new ArrayList<>();


    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    ClienteRepository clienteRepository;


    public List<Prestamo> getListaPrestamos(String dni) {
        Optional<Cliente> cliente = buscarCliente(dni);
        if (cliente.isPresent()) {
            return cliente.get().getPrestamos();
        } else {
            return null;
        }
    }


    public Optional<Cliente> buscarCliente(String dni) {
        return sistemaRepository.getListaClientes().stream().filter(c -> c.getDni().equals(dni)).findFirst();
    }

    public void addCliente(Cliente cliente) {
        sistemaRepository.getListaClientes().add(cliente);
    }

    public void crearLibro(Integer codigo, String nombre) {
        Libro libronuevo = new Libro(codigo, nombre);
        sistemaRepository.getItems().add(libronuevo);
    }


    public void añadirPrestamo(Cliente cliente, Prestamo prestamos) {
        cliente.getPrestamos().add(prestamos);
    }

    public void CrearCliente(Cliente cliente) {
        Cliente cli = new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getDomicilio());
    }




}
