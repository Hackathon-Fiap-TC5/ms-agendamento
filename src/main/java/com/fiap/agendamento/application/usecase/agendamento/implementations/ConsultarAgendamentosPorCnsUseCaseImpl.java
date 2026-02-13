package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentosPorCnsUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

import java.util.List;

public class ConsultarAgendamentosPorCnsUseCaseImpl implements ConsultarAgendamentosPorCnsUseCase {

    private final AgendamentoDomainService agendamentoDomainService;

    public ConsultarAgendamentosPorCnsUseCaseImpl(AgendamentoDomainService agendamentoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
    }

    @Override
    public List<AgendamentoDomain> consultarAgendamentosPorCns(String cns) {
        List<AgendamentoDomain> agendamentos = getAgendamentoDomains(cns);

        if (agendamentos.isEmpty()) {
            throw new AgendamentoNaoEncontradoException();
        }
        return agendamentos;
    }

    private List<AgendamentoDomain> getAgendamentoDomains(String cns) {
        return agendamentoDomainService.buscarTodosAgendamentosPorCns(cns);
    }
}
