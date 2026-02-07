package com.fiap.agendamento.application.usecase.agendamento;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

public interface ConsultarAgendamentoPorIdUseCase {

    AgendamentoDomain consultarAgendamentoPorId(Long idAgendamento);
}
