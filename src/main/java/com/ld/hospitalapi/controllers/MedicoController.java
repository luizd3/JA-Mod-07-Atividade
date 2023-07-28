package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.adapters.MedicosPorDepartamentoAdapter;
import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.MedicosPorDepartamento;
import com.ld.hospitalapi.services.MedicoService;
import com.ld.hospitalapi.views.MedicoCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicosPorDepartamentoAdapter medicosPorDepartamentoAdapter;

    @GetMapping
    public ResponseEntity<List<MedicoEntity>> findAll() {
        List<MedicoEntity> medicos = medicoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MedicoEntity> findById(@PathVariable("id") final Long id) {
        MedicoEntity medico = medicoService.findById(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @GetMapping(value = "/total-por-departamento", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<MedicoCount>> countTotalDoctorsByDepartment() {
        List<MedicoCount> medicosCountByDepartmentList =
                medicoService.countTotalDoctorsByDepartment();
        return new ResponseEntity<>(medicosCountByDepartmentList, HttpStatus.OK);
    }

    @GetMapping(value = "/por-departamento", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<MedicosPorDepartamento>> doctorsByDepartment() {
        List<MedicoEntity> medicos = medicoService.findAll();
        List<MedicosPorDepartamento> medicosByDepartments =
                medicosPorDepartamentoAdapter.adaptMedicosPorDepartamento(medicos);
        return new ResponseEntity<>(medicosByDepartments, HttpStatus.OK);
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
