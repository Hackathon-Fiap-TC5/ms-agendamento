package com.fiap.agendamento.infrastructure.database.repositories;

import com.fiap.agendamento.infrastructure.database.entities.AgendamentoEntity;
import com.fiap.agendamento.infrastructure.database.mappers.AgendamentoEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    List<AgendamentoEntity> findByCns(String cns);
}
