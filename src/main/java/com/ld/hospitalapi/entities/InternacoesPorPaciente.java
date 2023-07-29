package com.ld.hospitalapi.entities;

import java.util.List;

public class InternacoesPorPaciente {
    private PacienteEntity paciente;
    private List<InternacaoEntity> internacoes;

    public InternacoesPorPaciente(PacienteEntity paciente, List<InternacaoEntity> internacoes) {
        this.paciente = paciente;
        this.internacoes = internacoes;
    }

    public InternacoesPorPaciente() {
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public List<InternacaoEntity> getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(List<InternacaoEntity> internacoes) {
        this.internacoes = internacoes;
    }
}
