package com.ld.hospitalapi.repositories;

import com.ld.hospitalapi.entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

}
