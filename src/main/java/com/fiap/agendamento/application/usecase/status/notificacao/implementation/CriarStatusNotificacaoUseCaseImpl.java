package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.CriarStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.exception.StatusConsultaExistenteException;
import com.fiap.agendamento.domain.exception.StatusNotificacaoExistenteException;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class CriarStatusNotificacaoUseCaseImpl implements CriarStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;

    public CriarStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
    }

    @Override
    public void criarStatusNotificacao(StatusNotificacaoDomain domain) {
        statusNotificacaoGateway.buscarStatusNotificacaoPorDescricao(domain.getStatus())
                .ifPresent(status -> {
                    throw new StatusNotificacaoExistenteException();
                });

        statusNotificacaoGateway.criarStatusNotificacao(domain);
    }
}
