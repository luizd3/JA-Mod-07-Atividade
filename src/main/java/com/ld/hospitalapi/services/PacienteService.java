package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.PacienteEntity;
import com.ld.hospitalapi.repositories.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public PacienteEntity findById(Long id) {
        Optional<PacienteEntity> paciente = this.pacienteRepository.findById(id);
        if (paciente.isPresent())
            return paciente.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado");
    }
}
