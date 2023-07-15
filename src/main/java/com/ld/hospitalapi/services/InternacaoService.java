package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.repositories.InternacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternacaoService {

    private final InternacaoRepository internacaoRepository;

    public InternacaoService(InternacaoRepository internacaoRepository) {
        this.internacaoRepository = internacaoRepository;
    }

    public List<InternacaoEntity> findAll() {
        return this.internacaoRepository.findAll();
    }

    public void createNew(InternacaoEntity internacaoEntity) {
        this.internacaoRepository.save(internacaoEntity);
    }

    public void update(InternacaoEntity internacaoEntity) {
        this.internacaoRepository.save(internacaoEntity);
    }

    public void deleteById(Long id) {
        this.internacaoRepository.deleteById(id);
    }
}
