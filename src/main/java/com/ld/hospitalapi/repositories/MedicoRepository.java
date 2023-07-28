package com.ld.hospitalapi.repositories;

import com.ld.hospitalapi.entities.MedicoEntity;
import com.ld.hospitalapi.views.MedicoQuantPorDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    @Query("SELECT new com.ld.hospitalapi.views.MedicoQuantPorDepartamento(m.departamento, COUNT(m.departamento)) " +
            "FROM MedicoEntity m GROUP BY m.departamento ORDER BY m.departamento DESC")
    List<MedicoQuantPorDepartamento> countTotalDoctorsByDepartment();
}
