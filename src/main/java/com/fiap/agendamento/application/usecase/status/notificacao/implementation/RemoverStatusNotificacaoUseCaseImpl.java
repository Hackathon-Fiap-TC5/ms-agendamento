package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.RemoverStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class RemoverStatusNotificacaoUseCaseImpl implements RemoverStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;

    public RemoverStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway,
                                               StatusNotificacaoDomainService statusNotificacaoDomainService) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
    }

    @Override
    public void removerStatusNotificacao(Long id) {
        StatusNotificacaoDomain statusConsultaDomain = statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(id);
        statusNotificacaoGateway.removerStatus(statusConsultaDomain);
    }
}
