package com.ld.hospitalapi.adapters;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.entities.InternacoesPorPaciente;
import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.PacienteEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InternacoesPorPacienteAdapterTest {

    @Test
    public void adaptHospitalizationsByPacient() {
        PacienteEntity paciente1 = new PacienteEntity(1L, "Paciente 1", "11999999999", LocalDate.parse("2015-01-01"));
        PacienteEntity paciente2 = new PacienteEntity(2L, "Paciente 2", "11999999999", LocalDate.parse("2015-01-01"));

        MedicoEntity medico1 = new MedicoEntity(1L, "Médico 1", "Cargo 1", "Departamento 1", "11888888888");

        InternacaoEntity internacao1 = new InternacaoEntity(1L, paciente1, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico 1 do paciente 1", medico1);
        InternacaoEntity internacao2 = new InternacaoEntity(2L, paciente1, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico 2 do paciente 1", medico1);
        InternacaoEntity internacao3 = new InternacaoEntity(3L, paciente2, LocalDateTime.parse("2023-04-01T10:30:15"), LocalDateTime.parse("2023-04-03T07:11:56"), "Diagnóstico 1 do paciente 2", medico1);

        // Objeto esperado
        List<InternacaoEntity> internacoesDoPaciente1 = new ArrayList<>();
        internacoesDoPaciente1.add(internacao1);
        internacoesDoPaciente1.add(internacao2);

        List<InternacaoEntity> internacoesDoPaciente2 = new ArrayList<>();
        internacoesDoPaciente2.add(internacao3);

        InternacoesPorPaciente internacoesPorPaciente1 = new InternacoesPorPaciente(paciente1, internacoesDoPaciente1);
        InternacoesPorPaciente internacoesPorPaciente2 = new InternacoesPorPaciente(paciente2, internacoesDoPaciente2);

        List<InternacoesPorPaciente> internacoesPorPacienteEsperado = new ArrayList<>();
        internacoesPorPacienteEsperado.add(internacoesPorPaciente1);
        internacoesPorPacienteEsperado.add(internacoesPorPaciente2);

        // Chamando o adapter
        List<InternacaoEntity> internacoes = new ArrayList<>();
        internacoes.add(internacao1);
        internacoes.add(internacao2);
        internacoes.add(internacao3);

        InternacoesPorPacienteAdapter internacoesPorPacienteAdapter = new InternacoesPorPacienteAdapter();
        List<InternacoesPorPaciente> internacoesPorPacienteObtido =
                internacoesPorPacienteAdapter.adaptInternacoesPorPaciente(internacoes);

        Assertions.assertThat(internacoesPorPacienteEsperado)
                .usingRecursiveComparison().isEqualTo(internacoesPorPacienteObtido);
    }




}
