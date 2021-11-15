package com.example.examen.examen.controller;

import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteExistenteException;
import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteInexistenteException;
import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteVacioException;
import com.example.examen.examen.Exceptions.ItemExceptions.ItemExistenteException;
import com.example.examen.examen.Exceptions.ItemExceptions.ItemInexistenteException;
import com.example.examen.examen.entity.Cd;
import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.entity.ItemPrestamo;
import com.example.examen.examen.entity.Libro;
import com.example.examen.examen.repository.ItemPrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ItemController {


    @Autowired
    private ItemPrestamoRepository itemRepository;

    @GetMapping("/buscarItem/{id}")
    public ResponseEntity<ItemPrestamo> buscarItem(@PathVariable("id") Integer id) throws ItemInexistenteException {
        Optional<ItemPrestamo> item= itemRepository.buscarItem(id);
        if(item.isEmpty()){
            throw new ItemInexistenteException();
        }
        return ResponseEntity.ok(item.get());
    }

    @PostMapping("/crearCd")
    public void crearCd(@RequestBody Cd cd) throws ItemExistenteException {

        if (itemRepository.buscarItem(cd.getCodigo()).isPresent()) {
            throw new ItemExistenteException();
        }
        itemRepository.crearCd(cd.getCodigo(), cd.getNombre());
    }


    @PostMapping("/crearLibro")
    public void crearLibro(@RequestBody Libro libro) throws ItemExistenteException{
        if(itemRepository.buscarItem(libro.getCodigo()).isPresent()){
            throw new ItemExistenteException();
        }
        itemRepository.crearLibro(libro.getCodigo(), libro.getNombre());

    }

}
