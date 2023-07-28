package com.ld.hospitalapi.adapters;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.MedicosPorDepartamento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicosPorDepartamentoAdapter {

    public List<MedicosPorDepartamento> getMedicosPorDepartamento(List<MedicoEntity> medicos) {
        List<String> departamentos = medicos.stream()
                .map(MedicoEntity::getDepartamento).distinct().toList();

        List<MedicosPorDepartamento> medicosPorDepartamentoList = departamentos.stream().map(departamento -> {
            List<MedicoEntity> medicosPorDepartmento =
                    medicos.stream().filter(medico -> medico.getDepartamento().equals(departamento)).toList();
            List<String> nomesMedicosPorDepartamento =
                    medicosPorDepartmento.stream().map(MedicoEntity::getNome).toList();
            return new MedicosPorDepartamento(departamento, nomesMedicosPorDepartamento);
        }).toList();
        return medicosPorDepartamentoList;
    }
}
