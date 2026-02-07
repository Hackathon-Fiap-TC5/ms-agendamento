package com.fiap.agendamento.application.usecase.status.notificacao.implementation;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.AtualizarStatusNotificacaoUseCase;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public class AtualizarStatusNotificacaoUseCaseImpl implements AtualizarStatusNotificacaoUseCase {

    private final StatusNotificacaoGateway statusNotificacaoGateway;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;

    public AtualizarStatusNotificacaoUseCaseImpl(StatusNotificacaoGateway statusNotificacaoGateway,
                                                 StatusNotificacaoDomainService statusNotificacaoDomainService) {
        this.statusNotificacaoGateway = statusNotificacaoGateway;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
    }

    @Override
    public void atualizarStatusNotificacao(Long id, StatusNotificacaoDomain domain) {
        StatusNotificacaoDomain statusConsultaDomain = statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(id);
        statusNotificacaoDomainService.validarExistenciaStatusPorDescricao(domain);

        statusConsultaDomain.setStatus(domain.getStatus());
        statusNotificacaoGateway.atualizarStatusNotificacao(statusConsultaDomain);
    }
}
