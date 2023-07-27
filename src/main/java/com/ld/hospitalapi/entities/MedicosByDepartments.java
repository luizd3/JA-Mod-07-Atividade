package com.ld.hospitalapi.entities;

import java.util.List;

public class MedicosByDepartments {

    private String departamento;
    private List<String> medicos;

    public MedicosByDepartments(String departamento, List<String> medicos) {
        this.departamento = departamento;
        this.medicos = medicos;
    }

    public MedicosByDepartments() {
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<String> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<String> medicos) {
        this.medicos = medicos;
    }
}
