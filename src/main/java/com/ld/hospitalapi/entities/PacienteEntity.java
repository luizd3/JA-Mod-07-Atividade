package com.ld.hospitalapi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pacientes")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String nome;
    private String telefone;
    private Date data_nascimento;

    public PacienteEntity() {
    }

    public PacienteEntity(Long id, String nome, String telefone, Date data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
