package com.ld.hospitalapi.adapters;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.MedicosPorDepartamento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MedicosPorDepartamentoAdapterTest {

    @Test
    public void adaptDoctorsByDepartment() {
        MedicosPorDepartamentoAdapter adapter = new MedicosPorDepartamentoAdapter();
        List<MedicoEntity> medicos = new ArrayList<>();

        MedicoEntity medico1 = new MedicoEntity(1L, "Médico 1", "Cargo 1", "Departamento 1", "11888888888");
        MedicoEntity medico2 = new MedicoEntity(2L, "Médico 2", "Cargo 2", "Departamento 1", "11888888888");
        MedicoEntity medico3 = new MedicoEntity(3L, "Médico 3", "Cargo 3", "Departamento 1", "11888888888");
        MedicoEntity medico4 = new MedicoEntity(4L, "Médico 4", "Cargo 4", "Departamento 2", "11888888888");
        MedicoEntity medico5 = new MedicoEntity(5L, "Médico 5", "Cargo 5", "Departamento 2", "11888888888");

        medicos.add(medico1);
        medicos.add(medico2);
        medicos.add(medico3);
        medicos.add(medico4);
        medicos.add(medico5);

        // Objeto esperado
        List<MedicosPorDepartamento> expectedMedicosPorDepartamento = new ArrayList<>();

        List<String> medicosDoDepartamento1 = new ArrayList<>();
        medicosDoDepartamento1.add(medico1.getNome());
        medicosDoDepartamento1.add(medico2.getNome());
        medicosDoDepartamento1.add(medico3.getNome());

        List<String> medicosDoDepartamento2 = new ArrayList<>();
        medicosDoDepartamento2.add(medico4.getNome());
        medicosDoDepartamento2.add(medico5.getNome());

        expectedMedicosPorDepartamento.add(new MedicosPorDepartamento("Departamento 1", medicosDoDepartamento1));
        expectedMedicosPorDepartamento.add(new MedicosPorDepartamento("Departamento 2", medicosDoDepartamento2));

        // Chamando adapter para obter sua saída
        List<MedicosPorDepartamento> medicosPorDepartamentoList = adapter.adaptMedicosPorDepartamento(medicos);

        // Comparando se a saída do adapter é igual ao objeto esperado
        Assertions.assertThat(medicosPorDepartamentoList)
                .usingRecursiveComparison().isEqualTo(expectedMedicosPorDepartamento);
    }
}
