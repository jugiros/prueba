package com.prueba.prueba.controller;

import com.prueba.prueba.domain.Cliente;
import com.prueba.prueba.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes ()
    {
        return  clienteRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClienteById (@PathVariable(value = "id") Long id)
    {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente: " + id +" no existe"));
    }

    @PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public Cliente updatePrestamo(@PathVariable (value = "id") Long id, @Valid @RequestBody Cliente clienteRequest)
    {
        return clienteRepository.findById(id)
                .map( cli -> {
                    cli = clienteRequest;
                    return clienteRepository.save(cli);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cliente: " +id +" no existe"));
    }
}
