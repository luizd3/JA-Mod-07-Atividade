package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
