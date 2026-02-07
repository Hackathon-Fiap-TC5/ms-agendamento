package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.CriarStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class CriarStatusConsultaUseCaseImpl implements CriarStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;
    private final StatusConsultaDomainService statusConsultaDomainService;

    public CriarStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway,
                                          StatusConsultaDomainService statusConsultaDomainService) {
        this.statusConsultaGateway = statusConsultaGateway;
        this.statusConsultaDomainService = statusConsultaDomainService;
    }

    @Override
    public void criarStatusConsulta(StatusConsultaDomain domain) {
        statusConsultaDomainService.validarExistenciaStatusPorDescricao(domain);
        statusConsultaGateway.criarStatusConsulta(domain);
    }
}
