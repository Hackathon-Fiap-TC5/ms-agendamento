package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.RemoverStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class RemoverStatusConsultaUseCaseImpl implements RemoverStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;
    private final StatusConsultaDomainService statusConsultaDomainService;

    public RemoverStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway,
                                            StatusConsultaDomainService statusConsultaDomainService) {
        this.statusConsultaGateway = statusConsultaGateway;
        this.statusConsultaDomainService = statusConsultaDomainService;
    }

    @Override
    public void removerStatusConsulta(Long id) {
        StatusConsultaDomain domain = statusConsultaDomainService.buscarStatusConsultaDomainPorId(id);
        statusConsultaGateway.removerStatus(domain);
    }
}
