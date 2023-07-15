package com.ld.hospitalapi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_internacoes")
public class InternacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @Column(name = "entrada_paciente", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataEntradaPaciente;

    @Column(name = "saida_paciente", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataSaidaPaciente;

    private String diagnostico;

    @ManyToOne
    @JoinColumn(name = "matricula_medico")
    private MedicoEntity medico;

    public InternacaoEntity() {
    }

    public InternacaoEntity(Long id, PacienteEntity paciente, LocalDateTime dataEntradaPaciente, LocalDateTime dataSaidaPaciente, String diagnostico, MedicoEntity medico) {
        this.id = id;
        this.paciente = paciente;
        this.dataEntradaPaciente = dataEntradaPaciente;
        this.dataSaidaPaciente = dataSaidaPaciente;
        this.diagnostico = diagnostico;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataEntradaPaciente() {
        return dataEntradaPaciente;
    }

    public void setDataEntradaPaciente(LocalDateTime dataEntradaPaciente) {
        this.dataEntradaPaciente = dataEntradaPaciente;
    }

    public LocalDateTime getDataSaidaPaciente() {
        return dataSaidaPaciente;
    }

    public void setDataSaidaPaciente(LocalDateTime dataSaidaPaciente) {
        this.dataSaidaPaciente = dataSaidaPaciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }
}
