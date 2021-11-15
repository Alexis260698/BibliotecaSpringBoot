package com.example.examen.examen.repository;

import com.example.examen.examen.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemPrestamoRepository {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void crearLibro(Integer codigo, String nombre) {
        Libro libro = new Libro(codigo, nombre);
        sistemaRepository.getItems().add(libro);
    }

    public void crearCd(Integer codigo, String nombre) {
        Cd cd = new Cd(codigo, nombre);
        sistemaRepository.getItems().add(cd);
    }


    public Optional<ItemPrestamo> buscarItem(Integer codigo){
        return sistemaRepository.getItems().stream().filter(i -> i.getCodigo().equals(codigo)).findFirst();
    }


    public int getDiasDevolucion(Cliente cliente) {

        List<Prestamo> lista = clienteRepository.getListaPrestamos(cliente.getDni());

        int cant=0;
        for(Prestamo p: lista){
            if (p.getEstado().equalsIgnoreCase("pendiente")){
                if(!itemEsLibro(p.getItem().getClass()+"")){
                    cant+=1;
                }
            }
        }

        if(cant==0 || cant==1){
            return 4;
        }else if(cant ==2){
            return 2;
        }else {
            return 0;
        }

    }

    public boolean itemEsLibro(String clase) {
        int a = clase.indexOf("Libro");
        return a == -1 ? false : true;
    }


    public String getFechaDevolucion(LocalDate fecha, int dias){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedLocalDate = fecha.format(formatter);
        return sumarDias(formattedLocalDate + " 15:00:00",dias);
    }

    public static String sumarDias(String fecha, int days) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss");
        LocalDateTime fecha2 = LocalDateTime.parse(fecha, formateador);
        fecha2 = fecha2.plusDays(days);
        return fecha2.format(formateador).substring(0,10);
    }

}
