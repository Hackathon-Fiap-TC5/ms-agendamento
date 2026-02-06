package com.fiap.agendamento.infrastructure.database.repositories;

import com.fiap.agendamento.infrastructure.database.entities.StatusConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusConsultaRepository extends JpaRepository<StatusConsultaEntity, Long> {

}
