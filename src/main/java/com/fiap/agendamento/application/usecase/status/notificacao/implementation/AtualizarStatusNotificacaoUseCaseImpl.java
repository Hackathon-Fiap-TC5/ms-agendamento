package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.AtualizarStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.exception.StatusConsultaNaoEncontradoException;
import com.fiap.agendamento.domain.exception.StatusNotificacaoExistenteException;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class AtualizarStatusNotificacaoUseCaseImpl implements AtualizarStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;

    public AtualizarStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
    }

    @Override
    public void atualizarStatusNotificacao(Long id, StatusNotificacaoDomain domain) {
        StatusNotificacaoDomain statusConsultaDomain =
                statusNotificacaoGateway.buscarStatusNotificacaoPorId(id)
                        .orElseThrow(StatusConsultaNaoEncontradoException::new);

        statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao(domain.getStatus())
                .ifPresent(existente -> {
                    if (!existente.getId().equals(domain.getId())) {
                        throw new StatusNotificacaoExistenteException();
                    }
                });

        statusConsultaDomain.setStatus(domain.getStatus());
        statusNotificacaoGateway.atualizarStatusNotificacao(statusConsultaDomain);
    }
}
