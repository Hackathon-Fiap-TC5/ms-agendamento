package com.fiap.agendamento.application.gateway;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

import java.util.Optional;

public interface AgendamentoGateway {

    void criarOuAtualizarAgendamento(AgendamentoDomain agendamentoDomain);

    Optional<AgendamentoDomain> buscarAgendamentoPorId(Long idAgendamento);
}
