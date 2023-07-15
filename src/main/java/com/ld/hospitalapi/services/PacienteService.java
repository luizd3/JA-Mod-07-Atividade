package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.PacienteEntity;
import com.ld.hospitalapi.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteEntity> findAll() {
        return this.pacienteRepository.findAll();
    }

    public void createNew(PacienteEntity pacienteEntity) {
        this.pacienteRepository.save(pacienteEntity);
    }

    public void update(PacienteEntity pacienteEntity) {
        this.pacienteRepository.save(pacienteEntity);
    }

    public void deleteById(Long id) {
        this.pacienteRepository.deleteById(id);
    }
}
