package com.example.examen.examen.repository;


import com.example.examen.examen.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Getter @Setter
public class SistemaRepository {

    private List<Prestamo> listaPrestamos= new ArrayList<>();
    private List<Cliente> listaClientes= new ArrayList<>();
    private List<ItemPrestamo> items= new ArrayList<>();


    public Optional<Cliente> buscarCliente(String dni){
        return listaClientes.stream().filter(c -> c.getDni().equals(dni)).findFirst();
    }

    public void addCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    public void crearLibro(Integer codigo, String nombre){
        Libro libronuevo= new Libro(codigo, nombre);
        items.add(libronuevo);
    }

    public void crearCd(Integer codigo, String nombre){
        Cd cdnuevo= new Cd(codigo, nombre);
        items.add(cdnuevo);
    }

    public void crearPrestamo(Integer dni, Integer codigo){
        Optional<ItemPrestamo> optionalItem=buscarItem(codigo);
        Optional<Cliente> optionalCliente=buscarCliente(dni+"");

        if(optionalItem.isPresent() && optionalCliente.isPresent()){
            /* // obtener la fecha de devolucion

                Prestamo prestamoNuevo= new Prestamo(LocalDate.now(),optionalItem, ,"pendiente");

             //Agregar el prestamo nuevo al cliente correspondiente
             //agregar el prestamo a la lista

             */
        }else{
            //notificar que no se puede crear el prestamo
        }
    }

    public void añadirPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }

    public Optional<ItemPrestamo> buscarItem(Integer codigo){
        return items.stream().filter(item -> item.getCodigo().equals(codigo)).findFirst();
    }

}
