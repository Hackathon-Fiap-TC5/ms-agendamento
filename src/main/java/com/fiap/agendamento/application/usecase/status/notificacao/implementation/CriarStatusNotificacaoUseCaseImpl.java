package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.CriarStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class CriarStatusNotificacaoUseCaseImpl implements CriarStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;

    public CriarStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway,
                                             StatusNotificacaoDomainService statusNotificacaoDomainService) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
    }

    @Override
    public void criarStatusNotificacao(StatusNotificacaoDomain domain) {
        statusNotificacaoDomainService.validarExistenciaStatusPorDescricao(domain);
        statusNotificacaoGateway.criarStatusNotificacao(domain);
    }
}
