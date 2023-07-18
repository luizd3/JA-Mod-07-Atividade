package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.entities.PacienteEntity;
import com.ld.hospitalapi.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8") // Necessário para não dar erro de codificação nos testes
    public ResponseEntity<List<PacienteEntity>> findAll() {
        return new ResponseEntity<>(this.pacienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8") // Necessário para não dar erro de codificação nos testes
    public ResponseEntity<String> createNew(@RequestBody final PacienteEntity pacienteEntity) {
        this.pacienteService.createNew(pacienteEntity);
        String message = "Paciente cadastrado com sucesso.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody final PacienteEntity pacienteEntity) {
        this.pacienteService.update(pacienteEntity);
        String message = "Paciente atualizado";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        this.pacienteService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
