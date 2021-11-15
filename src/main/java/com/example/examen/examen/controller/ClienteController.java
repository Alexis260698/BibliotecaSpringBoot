package com.example.examen.examen.controller;

import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteExistenteException;
import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteInexistenteException;
import com.example.examen.examen.Exceptions.ClienteExceptions.ClienteVacioException;
import com.example.examen.examen.entity.Cliente;
import com.example.examen.examen.entity.Prestamo;
import com.example.examen.examen.repository.ClienteRepository;
import com.example.examen.examen.repository.PrestamoRepository;
import com.example.examen.examen.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;


    @GetMapping("/buscarCliente/{dni}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable("dni") String dni) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente = clienteRepository.buscarCliente(dni);

        if(optionalCliente.isEmpty()){
            throw new ClienteInexistenteException();
        }
        return ResponseEntity.ok(optionalCliente.get());
    }


    @PostMapping("/nuevoCliente")
    public void añadirCliente(@RequestBody Cliente cliente) throws ClienteVacioException, ClienteExistenteException {
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
                clienteRepository.addCliente(cliente);
            }else{
                throw new ClienteExistenteException();
            }
        }
    }

    @DeleteMapping("/eliminarCliente/{dni}")
    public void eliminarCliente(@PathVariable("dni") String dni) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente= clienteRepository.buscarCliente(dni);
        if(optionalCliente.isPresent()){
            optionalCliente.ifPresent(value -> sistemaRepository.getListaClientes().remove(value));
        }else{


            throw new ClienteInexistenteException();
        }

    }

    @PutMapping("/actualizarCliente")
    public void actualizarCliente(@RequestBody Cliente cliente) throws ClienteInexistenteException {
        Optional<Cliente> optionalCliente= clienteRepository.buscarCliente(cliente.getDni());
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

    @GetMapping("/prestamos/{dni}")
    public List<Prestamo> getClientes(@PathVariable("dni") String dni) {
        return clienteRepository.getListaPrestamos(dni);
    }

}




