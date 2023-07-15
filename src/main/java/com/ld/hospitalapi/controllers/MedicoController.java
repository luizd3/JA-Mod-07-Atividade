package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.services.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoEntity>> findAll() {
        List<MedicoEntity> medicos = medicoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNew(@RequestBody final MedicoEntity medicoEntity) {
        this.medicoService.createNew(medicoEntity);
        String message = "Médico cadastrado";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody final MedicoEntity medicoEntity) {
        this.medicoService.update(medicoEntity);
        String message = "Atualização realizada";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        this.medicoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
