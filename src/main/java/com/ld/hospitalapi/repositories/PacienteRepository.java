package com.ld.hospitalapi.repositories;

import com.ld.hospitalapi.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

}
