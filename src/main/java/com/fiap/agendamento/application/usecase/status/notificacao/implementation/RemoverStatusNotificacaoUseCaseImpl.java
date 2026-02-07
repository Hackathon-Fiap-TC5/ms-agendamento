package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.RemoverStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.exception.StatusNotificacaoNaoEncontradoException;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class RemoverStatusNotificacaoUseCaseImpl implements RemoverStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;

    public RemoverStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
    }

    @Override
    public void removerStatusNotificacao(Long id) {
        StatusNotificacaoDomain domain = statusNotificacaoGateway.buscarStatusNotificacaoPorId(id)
                .orElseThrow(StatusNotificacaoNaoEncontradoException::new);

        statusNotificacaoGateway.removerStatus(domain);
    }
}
