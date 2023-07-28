package com.ld.hospitalapi.repositories;

import com.ld.hospitalapi.entities.InternacaoEntity;
import com.ld.hospitalapi.views.InternacaoQuantPorPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InternacaoRepository extends JpaRepository<InternacaoEntity, Long> {

    @Query("SELECT new com.ld.hospitalapi.views.InternacaoQuantPorPaciente(p.nome, count(hi.paciente))" +
            "  FROM InternacaoEntity hi" +
            " INNER JOIN PacienteEntity p ON hi.paciente = p" +
            " GROUP BY p.nome" +
            " ORDER BY count(hi.paciente) DESC")
    List<InternacaoQuantPorPaciente> countTotalHospitalizationsByPacient();
}
