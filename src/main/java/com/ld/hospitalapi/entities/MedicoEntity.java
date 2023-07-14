package com.ld.hospitalapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long matricula;

    private String nome;
    private String cargo;
    private String departamento;
    private String telefone;

    public MedicoEntity() {
    }

    public MedicoEntity(Long matricula, String nome, String cargo, String departamento, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.departamento = departamento;
        this.telefone = telefone;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
