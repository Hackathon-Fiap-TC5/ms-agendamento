package com.fiap.agendamento.application.usecase.agendamento;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

import java.util.List;

public interface ConsultarAgendamentosPorCnsUseCase {

    List<AgendamentoDomain> consultarAgendamentosPorCns(String cns);
}
