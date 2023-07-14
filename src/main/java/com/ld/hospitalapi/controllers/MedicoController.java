package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.repositories.MedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping
    public ResponseEntity<List<MedicoEntity>> findAll() {
        List<MedicoEntity> medicos = medicoRepository.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNew(@RequestBody final MedicoEntity medicoEntity) {
        this.medicoRepository.save(medicoEntity);
        String message = "Médico cadastrado";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody final MedicoEntity medicoEntity) {
        this.medicoRepository.save(medicoEntity);
        String message = "Atualização realizada";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") final Long id) {
        this.medicoRepository.deleteById(id);
        String message = "Registro de médico apagado";
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }
}
