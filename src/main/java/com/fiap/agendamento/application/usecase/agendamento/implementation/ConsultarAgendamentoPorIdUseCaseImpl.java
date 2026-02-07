package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentoPorIdUseCase;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class ConsultarAgendamentoPorIdUseCaseImpl implements ConsultarAgendamentoPorIdUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ConsultarAgendamentoPorIdUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public AgendamentoDomain consultarAgendamentoPorId(Long idAgendamento) {
        return agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);
    }
}
