package com.ld.hospitalapi.views;

public class InternacaoQuantPorPaciente {
    private String nomePaciente;
    private Long quantidadeDeInternacoes;

    public InternacaoQuantPorPaciente(String nomePaciente, Long quantidadeDeInternacoes) {
        this.nomePaciente = nomePaciente;
        this.quantidadeDeInternacoes = quantidadeDeInternacoes;
    }

    public InternacaoQuantPorPaciente() {
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Long getQuantidadeDeInternacoes() {
        return quantidadeDeInternacoes;
    }

    public void setQuantidadeDeInternacoes(Long quantidadeDeInternacoes) {
        this.quantidadeDeInternacoes = quantidadeDeInternacoes;
    }
}
