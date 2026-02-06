package com.fiap.agendamento.infrastructure.database.repositories;

import com.fiap.agendamento.infrastructure.database.entities.StatusNotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusNotificacaoRepository extends JpaRepository<StatusNotificacaoEntity, Long> {

}
