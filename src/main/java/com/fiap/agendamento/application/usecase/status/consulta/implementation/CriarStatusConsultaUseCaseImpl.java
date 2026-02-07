package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.CriarStatusConsultaUseCase;
import com.fiap.agendamento.domain.exception.StatusConsultaExistenteException;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class CriarStatusConsultaUseCaseImpl implements CriarStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;

    public CriarStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway) {
        this.statusConsultaGateway = statusConsultaGateway;
    }

    @Override
    public void criarStatusConsulta(StatusConsultaDomain domain) {
        statusConsultaGateway.buscarStatusConsultaPorDescricao(domain.getStatus())
                .ifPresent(status -> {
                    throw new StatusConsultaExistenteException();
                });

        statusConsultaGateway.criarStatusConsulta(domain);
    }
}
