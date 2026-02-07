package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarStatusConsultaUseCase;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class RegistrarStatusConsultaUseCaseImpl implements RegistrarStatusConsultaUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public RegistrarStatusConsultaUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void registrarStatusConsulta(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);

        buildAgendamentoDomain(agendamentoDomain, domain);
        agendamentoGateway.criarOuAtualizarAgendamento(domain);
    }

    private static void buildAgendamentoDomain(AgendamentoDomain agendamentoDomain, AgendamentoDomain domain) {
        StatusConsultaDomain statusConsultaDomain = new StatusConsultaDomain();
        statusConsultaDomain.setId(agendamentoDomain.getStatusConsultaDomain().getId());
        domain.setStatusConsultaDomain(statusConsultaDomain);
    }
}
