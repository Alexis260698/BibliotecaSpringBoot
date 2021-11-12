package com.example.examen.examen.repository;

import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.entity.Prestamo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter @Setter
public class ClienteRepository {

   private List<Prestamo> listaPrestamos= new ArrayList<>();

   public List<Prestamo> getListaPrestamos(String dni){

    return null;
   }

   public void addPrestamo(Prestamo prestamos){
       listaPrestamos.add(prestamos);
   }

   public void CrearCliente(Cliente cliente){
       Cliente cli= new Cliente(cliente.getNombre(),cliente.getDni(),cliente.getDomicilio());
   }


}
