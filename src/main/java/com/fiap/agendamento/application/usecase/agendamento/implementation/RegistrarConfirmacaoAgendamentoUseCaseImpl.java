package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class RegistrarConfirmacaoAgendamentoUseCaseImpl implements RegistrarConfirmacaoAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public RegistrarConfirmacaoAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void registrarConfirmacaoAgendamento(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);

        buildAgendamentoDomain(agendamentoDomain, domain);
        agendamentoGateway.criarOuAtualizarAgendamento(domain);
    }

    private static void buildAgendamentoDomain(AgendamentoDomain agendamentoDomain, AgendamentoDomain domain) {
        StatusNotificacaoDomain statusNotificacaoDomain = new StatusNotificacaoDomain();
        statusNotificacaoDomain.setId(agendamentoDomain.getStatusNotificacaoDomain().getId());
        domain.setStatusNotificacaoDomain(statusNotificacaoDomain);
    }
}
