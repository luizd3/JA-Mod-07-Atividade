package com.ld.hospitalapi.controllers;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.services.InternacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/internacoes")
public class InternacaoController {

    private final InternacaoService internacaoService;

    public InternacaoController(InternacaoService internacaoService) {
        this.internacaoService = internacaoService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8") // Necessário para não dar erro de codificação nos testes
    public ResponseEntity<List<InternacaoEntity>> findAll() {
        return new ResponseEntity<>(this.internacaoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<InternacaoEntity> findById(@PathVariable final Long id) {
        return new ResponseEntity<>(this.internacaoService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8") // Necessário para não dar erro de codificação nos testes
    public ResponseEntity<String> createNew(@RequestBody final InternacaoEntity internacaoEntity) {
        this.internacaoService.createNew(internacaoEntity);
        String message = "Internação registrada com sucesso.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody final InternacaoEntity internacaoEntity) {
        this.internacaoService.update(internacaoEntity);
        String message = "Internação atualizada com sucesso.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        this.internacaoService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
