package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentosPorCnsUseCase;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

import java.util.List;

public class ConsultarAgendamentosPorCnsUseCaseImpl implements ConsultarAgendamentosPorCnsUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ConsultarAgendamentosPorCnsUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public List<AgendamentoDomain> consultarAgendamentosPorCns(String cns) {
        return agendamentoGateway.buscarTodosAgendamentosPorCns(cns);
    }
}
