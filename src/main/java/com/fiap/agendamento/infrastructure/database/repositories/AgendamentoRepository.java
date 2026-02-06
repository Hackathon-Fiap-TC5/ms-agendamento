package com.fiap.agendamento.infrastructure.database.repositories;

import com.fiap.agendamento.infrastructure.database.entities.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

}
