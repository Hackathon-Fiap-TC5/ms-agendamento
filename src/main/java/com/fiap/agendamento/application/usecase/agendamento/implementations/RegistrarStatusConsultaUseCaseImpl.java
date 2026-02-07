package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.RegistrarStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class RegistrarStatusConsultaUseCaseImpl implements RegistrarStatusConsultaUseCase {

    private final AgendamentoDomainService agendamentoDomainService;

    public RegistrarStatusConsultaUseCaseImpl(AgendamentoDomainService agendamentoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
    }

    @Override
    public void registrarStatusConsulta(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
        buildAgendamentoDomain(agendamentoDomain, domain);
        agendamentoDomainService.criarOuAtualizarAgendamento(domain);
    }

    private static void buildAgendamentoDomain(AgendamentoDomain agendamentoDomain, AgendamentoDomain domain) {
        StatusConsultaDomain statusConsultaDomain = new StatusConsultaDomain();
        statusConsultaDomain.setId(agendamentoDomain.getStatusConsultaDomain().getId());
        domain.setStatusConsultaDomain(statusConsultaDomain);
    }
}
