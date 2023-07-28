package com.ld.hospitalapi.views;

public class MedicoQuantPorDepartamento {
    private String departamento;
    private Long quantidadeDeMedicos;

    public MedicoQuantPorDepartamento(String departamento, Long quantidadeDeMedicos) {
        this.departamento = departamento;
        this.quantidadeDeMedicos = quantidadeDeMedicos;
    }

    public MedicoQuantPorDepartamento() {
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Long getQuantidadeDeMedicos() {
        return quantidadeDeMedicos;
    }

    public void setQuantidadeDeMedicos(Long quantidadeDeMedicos) {
        this.quantidadeDeMedicos = quantidadeDeMedicos;
    }
}
