package com.fiap.agendamento.domain.domain.service;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

import java.util.List;

public interface AgendamentoDomainService {

    AgendamentoDomain buscarAgendamentoDomainPorId(Long idAgendamento);

    List<AgendamentoDomain> buscarTodosAgendamentosPorCns(String cns);

    AgendamentoDomain criarOuAtualizarAgendamento(AgendamentoDomain domain);
}
