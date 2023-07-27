package com.ld.hospitalapi.adapters;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.entities.MedicosByDepartments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicosByDepartmentsAdapter {

    public List<MedicosByDepartments> getMedicosByDepartments(List<MedicoEntity> medicos) {
        List<String> departamentos = medicos.stream()
                .map(MedicoEntity::getDepartamento).distinct().toList();

        List<MedicosByDepartments> medicosByDepartments = departamentos.stream().map(departamento -> {
            List<MedicoEntity> medicosByDepartment =
                    medicos.stream().filter(medico -> medico.getDepartamento().equals(departamento)).toList();
            List<String> nomesMedicosByDepartment =
                    medicosByDepartment.stream().map(MedicoEntity::getNome).toList();
            return new MedicosByDepartments(departamento, nomesMedicosByDepartment);
        }).toList();
        return medicosByDepartments;
    }
}
