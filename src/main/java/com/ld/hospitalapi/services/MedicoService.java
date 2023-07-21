package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.repositories.MedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<MedicoEntity> findAll() {
        return this.medicoRepository.findAll();
    }

    public void createNew(MedicoEntity medicoEntity) {
        this.medicoRepository.save(medicoEntity);
    }

    public void update(MedicoEntity medicoEntity) {
        this.medicoRepository.save(medicoEntity);
    }

    public void deleteById(Long id) {
        this.medicoRepository.deleteById(id);
    }

    public MedicoEntity findById(Long id) {
        Optional<MedicoEntity> medico = this.medicoRepository.findById(id);
        if (medico.isPresent())
            return medico.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado");
    }
}
