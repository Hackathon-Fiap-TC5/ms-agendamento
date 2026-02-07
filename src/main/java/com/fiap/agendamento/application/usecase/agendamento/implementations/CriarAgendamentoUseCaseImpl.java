package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.CriarAgendamentoUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

    private final AgendamentoDomainService agendamentoDomainService;

    public CriarAgendamentoUseCaseImpl(AgendamentoDomainService agendamentoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
    }

    @Override
    public void criarAgendamento(AgendamentoDomain agendamentoDomain) {
        agendamentoDomainService.criarOuAtualizarAgendamento(agendamentoDomain);
    }
}
