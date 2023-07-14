package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.repositories.MedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final MedicoRepository medicoRepository;

    public HospitalController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoEntity>> findAll() {
        List<MedicoEntity> medicos = medicoRepository.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }
}
