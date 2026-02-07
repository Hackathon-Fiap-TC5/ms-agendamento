package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.exception.StatusConsultaExistenteException;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public class StatusConsultaDomainServiceImpl implements StatusConsultaDomainService {

    private final StatusConsultaGateway statusConsultaGateway;

    public StatusConsultaDomainServiceImpl(StatusConsultaGateway statusConsultaGateway) {
        this.statusConsultaGateway = statusConsultaGateway;
    }

    @Override
    public StatusConsultaDomain buscarStatusConsultaDomainPorId(Long id) {
        return statusConsultaGateway.buscarStatusConsultaPorId(id)
                .orElseThrow(StatusConsultaNaoEncontradoException::new);
    }

    @Override
    public void validarExistenciaStatusPorDescricao(StatusConsultaDomain domain) {
        statusConsultaGateway.buscarStatusConsultaPorDescricao(domain.getStatus())
                .ifPresent(existente -> {
                    if (!existente.getId().equals(domain.getId())) {
                        throw new StatusConsultaExistenteException();
                    }
                });
    }
}