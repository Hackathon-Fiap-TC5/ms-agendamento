package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class RegistrarConfirmacaoAgendamentoUseCaseImpl implements RegistrarConfirmacaoAgendamentoUseCase {

    private final AgendamentoDomainService agendamentoDomainService;

    public RegistrarConfirmacaoAgendamentoUseCaseImpl(AgendamentoDomainService agendamentoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
    }

    @Override
    public void registrarConfirmacaoAgendamento(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
        buildAgendamentoDomain(agendamentoDomain, domain);
        agendamentoDomainService.criarOuAtualizarAgendamento(domain);
    }

    private static void buildAgendamentoDomain(AgendamentoDomain agendamentoDomain, AgendamentoDomain domain) {
        StatusNotificacaoDomain statusNotificacaoDomain = new StatusNotificacaoDomain();
        statusNotificacaoDomain.setId(agendamentoDomain.getStatusNotificacaoDomain().getId());
        domain.setStatusNotificacaoDomain(statusNotificacaoDomain);
    }
}
