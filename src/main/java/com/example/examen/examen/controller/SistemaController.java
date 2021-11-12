package com.example.examen.examen.controller;


import com.example.examen.examen.Exceptions.ClienteExistenteException;
import com.example.examen.examen.Exceptions.ClienteInexistenteException;
import com.example.examen.examen.Exceptions.ClienteVacioException;
import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.repository.ClienteRepository;
import com.example.examen.examen.repository.SistemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/Sistema")
public class SistemaController {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("/buscarCliente/{dni}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable("dni") String dni) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente = sistemaRepository.buscarCliente(dni);

        if(optionalCliente.isEmpty()){
            throw new ClienteInexistenteException();
        }
        return ResponseEntity.ok(optionalCliente.get());
    }

    @PostMapping("/nuevoCliente")
    public void addProduct(@RequestBody Cliente cliente) throws ClienteVacioException, ClienteExistenteException {
        boolean existeCliente=true;

        if(clienteConCamposVacios(cliente)){
            throw new ClienteVacioException();
        }else{

            try {
                buscarCliente(cliente.getDni());
            }catch(ClienteInexistenteException e){
                existeCliente=false;
            }

            if(!existeCliente){
                sistemaRepository.addCliente(cliente);
            }else{
                throw new ClienteExistenteException();
            }
        }
    }

    @DeleteMapping("/eliminarCliente/{dni}")
    public void eliminarCliente(@PathVariable("dni") String dni) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente= sistemaRepository.buscarCliente(dni);
        if(optionalCliente.isPresent()){
            optionalCliente.ifPresent(value -> sistemaRepository.getListaClientes().remove(value));
        }else{
            throw new ClienteInexistenteException();
        }

    }

    @PutMapping("/actualizarCliente")
    public void actualizarCliente(@RequestBody Cliente cliente) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente= sistemaRepository.buscarCliente(cliente.getDni());
        if(optionalCliente.isPresent()){
            for(Cliente c: sistemaRepository.getListaClientes()){
                if(c.getDni().equals(cliente.getDni())){
                    c.setDni(cliente.getDni());
                    c.setNombre(cliente.getNombre());
                    c.setDomicilio(cliente.getDomicilio());
                }

            }
        }else{
            throw new ClienteInexistenteException();
        }


    }


     public boolean clienteConCamposVacios(Cliente cliente){
        if(cliente.getDni().equals("") || cliente.getDni().isEmpty() ||
                cliente.getNombre().equals("") || cliente.getNombre().isEmpty() ||
                cliente.getDomicilio().equals("") || cliente.getDomicilio().isEmpty() ){
            return true;
        }else{
            return false;
        }
     }

}
