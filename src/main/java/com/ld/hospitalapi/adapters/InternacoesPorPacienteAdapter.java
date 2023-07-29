package com.ld.hospitalapi.adapters;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.entities.InternacoesPorPaciente;
import com.ld.hospitalapi.entities.PacienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InternacoesPorPacienteAdapter {

    public List<InternacoesPorPaciente> adaptInternacoesPorPaciente(List<InternacaoEntity> internacoes) {
        List<PacienteEntity> pacientes = internacoes.stream()
                .map(InternacaoEntity::getPaciente).distinct().toList();

        List<InternacoesPorPaciente> internacoesPorPacienteList =
                pacientes.stream().map(paciente -> {
                    List<InternacaoEntity> internacoesFiltradas = internacoes.stream()
                            .filter(internacao -> internacao.getPaciente().equals(paciente)).toList();
                    InternacoesPorPaciente internacoesPorPaciente = new InternacoesPorPaciente();
                    internacoesPorPaciente.setPaciente(paciente);
                    internacoesPorPaciente.setInternacoes(internacoesFiltradas);
                    return internacoesPorPaciente;
                }).toList();

        return internacoesPorPacienteList;
    }
}
