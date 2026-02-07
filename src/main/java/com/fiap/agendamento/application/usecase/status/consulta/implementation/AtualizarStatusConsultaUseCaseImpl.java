package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.AtualizarStatusConsultaUseCase;
import com.fiap.agendamento.domain.exception.StatusConsultaExistenteException;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class AtualizarStatusConsultaUseCaseImpl implements AtualizarStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;

    public AtualizarStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway) {
        this.statusConsultaGateway = statusConsultaGateway;
    }

    @Override
    public void atualizarStatusConsulta(Long id, StatusConsultaDomain domain) {
        StatusConsultaDomain statusConsultaDomain =
                statusConsultaGateway.buscarStatusConsultaPorId(id)
                        .orElseThrow(StatusConsultaNaoEncontradoException::new);

        statusConsultaGateway.buscarStatusConsultaPorDescricao(domain.getStatus())
                .ifPresent(existente -> {
                    if (!existente.getId().equals(domain.getId())) {
                        throw new StatusConsultaExistenteException();
                    }
                });

        statusConsultaDomain.setStatus(domain.getStatus());
        statusConsultaGateway.atualizarStatusConsulta(statusConsultaDomain);
    }
}