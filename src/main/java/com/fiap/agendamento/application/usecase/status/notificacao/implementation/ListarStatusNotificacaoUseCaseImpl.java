package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.ListarStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

import java.util.List;

public class ListarStatusNotificacaoUseCaseImpl implements ListarStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;

    public ListarStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
    }

    @Override
    public List<StatusNotificacaoDomain> listarStatusConsultas() {
        return statusNotificacaoGateway.buscarTodosOsStatus();
    }
}
