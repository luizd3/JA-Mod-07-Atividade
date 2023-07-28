package com.ld.hospitalapi.services;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.repositories.InternacaoRepository;
import com.ld.hospitalapi.views.InternacaoQuantPorPaciente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public InternacaoEntity findById(Long id) {
        Optional<InternacaoEntity> internacao = this.internacaoRepository.findById(id);
        if(internacao.isPresent())
            return internacao.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Internação não encontrada");
    }

    public List<InternacaoQuantPorPaciente> countTotalHospitalizationsByPacient() {
        return this.internacaoRepository.countTotalHospitalizationsByPacient();
    }
}
