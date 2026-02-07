package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.exception.StatusNotificacaoExistenteException;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class StatusNotificacaoDomainServiceImpl implements StatusNotificacaoDomainService {

    private final StatusNotificacaoGateway statusNotificacaoGateway;

    public StatusNotificacaoDomainServiceImpl(StatusNotificacaoGateway statusNotificacaoGateway) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
    }

    @Override
    public StatusNotificacaoDomain buscarStatusNotificacaoDomainPorId(Long id) {
        return statusNotificacaoGateway.buscarStatusNotificacaoPorId(id)
                        .orElseThrow(StatusConsultaNaoEncontradoException::new);
    }

    @Override
    public void validarExistenciaStatusPorDescricao(StatusNotificacaoDomain domain) {
        statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao(domain.getStatus())
                .ifPresent(existente -> {
                    if (!existente.getId().equals(domain.getId())) {
                        throw new StatusNotificacaoExistenteException();
                    }
                });
    }
}
