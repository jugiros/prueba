package com.prueba.prueba.controller;

import com.prueba.prueba.domain.Prestamo;
import com.prueba.prueba.repository.ClienteRepository;
import com.prueba.prueba.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class PrestamoController {

    private final PrestamoRepository prestamoRepository;

    @Autowired
    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping("/prestamos")
    public List<Prestamo> getAllPrestamos  ()
    {
        return  prestamoRepository.findAll();
    }

    @GetMapping("/prestamos/{id}")
    public Prestamo getPrestamoById (@PathVariable(value = "id") Long id)
    {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo: " + id +" no existe"));
    }

    @PostMapping("/prestamos")
    public Prestamo createPrestamo(@Valid @RequestBody Prestamo cliente)
    {
        return prestamoRepository.save(cliente);
    }

    @PutMapping("/prestamos/{id}")
    public Prestamo updatePrestamo(@PathVariable (value = "id") Long id, @Valid @RequestBody Prestamo prestamoRequest)
    {
        return prestamoRepository.findById(id)
                .map( pre -> {
                    pre = prestamoRequest;
                    return prestamoRepository.save(pre);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo: " +id +" no existe"));
    }

}
