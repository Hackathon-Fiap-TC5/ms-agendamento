package com.fiap.agendamento.application.usecase.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.CriarAgendamentoUseCase;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void criarAgendamento(AgendamentoDomain agendamentoDomain) {
        agendamentoGateway.criarOuAtualizarAgendamento(agendamentoDomain);
    }
}
