package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.AtualizarStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class AtualizarStatusConsultaUseCaseImpl implements AtualizarStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;
    private final StatusConsultaDomainService statusConsultaDomainService;

    public AtualizarStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway,
                                              StatusConsultaDomainService statusConsultaDomainService) {
        this.statusConsultaGateway = statusConsultaGateway;
        this.statusConsultaDomainService = statusConsultaDomainService;
    }

    @Override
    public void atualizarStatusConsulta(Long id, StatusConsultaDomain domain) {
        StatusConsultaDomain statusConsultaDomain = statusConsultaDomainService.buscarStatusConsultaDomainPorId(id);
        statusConsultaDomainService.validarExistenciaStatusPorDescricao(domain);

        statusConsultaDomain.setStatus(domain.getStatus());
        statusConsultaGateway.atualizarStatusConsulta(statusConsultaDomain);
    }
}