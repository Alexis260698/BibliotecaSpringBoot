package com.example.examen.examen.repository;

import com.example.examen.examen.Exceptions.GeneralsExceptions.ErrorException;
import com.example.examen.examen.Exceptions.GeneralsExceptions.ExcesoDePrestamos;
import com.example.examen.examen.Exceptions.GeneralsExceptions.cantCreateExaption;
import com.example.examen.examen.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public class PrestamoRepository {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPrestamoRepository itemPrestamoRepository;

    @Autowired
    private ModelMapper modelMapper;


    public boolean itemEsLibro(String clase) {
        int a = clase.indexOf("Libro");
        return a == -1 ? false : true;
    }

    public void crearPrestamo(Integer dni, Integer codigo) throws cantCreateExaption, ExcesoDePrestamos {
        Optional<ItemPrestamo> optionalItem = itemPrestamoRepository.buscarItem(codigo);
        Optional<Cliente> optionalCliente = clienteRepository.buscarCliente(dni + "");

        if (optionalItem.isPresent() && optionalCliente.isPresent()) {

            ItemPrestamo item = optionalItem.get();

            if (itemEsLibro(item.getClass() + "")) {

                Libro libro = new Libro(item.getCodigo(), item.getNombre());
                Prestamo prestamoNuevo = new Prestamo(LocalDate.now(), libro,
                        itemPrestamoRepository.getFechaDevolucion(LocalDate.now(),7), "pendiente");

                clienteRepository.añadirPrestamo(optionalCliente.get(), prestamoNuevo);
                añadirPrestamo(prestamoNuevo);


            } else {
                int dias=itemPrestamoRepository.getDiasDevolucion(optionalCliente.get());

                if(dias!=0){
                Cd cd= new Cd(optionalItem.get().getCodigo(),optionalItem.get().getNombre());

                Prestamo prestamoNuevo= new Prestamo(LocalDate.now(),cd,
                        itemPrestamoRepository.getFechaDevolucion(LocalDate.now(),dias),"pendiente");

                clienteRepository.añadirPrestamo(optionalCliente.get(), prestamoNuevo);
                añadirPrestamo(prestamoNuevo);


                }else{
                    throw new ExcesoDePrestamos();
                }

            }


        } else {
            throw new cantCreateExaption();
            //notificar que no se puede crear el prestamo
        }
    }

    public void añadirPrestamo(Prestamo prestamo) {

        sistemaRepository.getListaPrestamos().add(prestamo);
    }

}
