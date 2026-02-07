package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.RemoverStatusConsultaUseCase;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class RemoverStatusConsultaUseCaseImpl implements RemoverStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;

    public RemoverStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway) {
        this.statusConsultaGateway = statusConsultaGateway;
    }

    @Override
    public void removerStatusConsulta(Long id) {
        StatusConsultaDomain domain = statusConsultaGateway.buscarStatusConsultaPorId(id)
                .orElseThrow(StatusConsultaNaoEncontradoException::new);

        statusConsultaGateway.removerStatus(domain);
    }
}
