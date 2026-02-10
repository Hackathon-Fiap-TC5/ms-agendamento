package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.CancelarAgendamentoPorIdUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.publisher.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.publisher.AgendamentoPublisher;

import java.time.OffsetDateTime;

public class CancelarAgendamentoPorIdUseCaseImpl implements CancelarAgendamentoPorIdUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final StatusConsultaDomainService statusConsultaDomainService;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;
    private final AgendamentoGateway agendamentoGateway;

    private final AgendamentoPublisher agendamentoPublisher;

    public static final Long STATUS_CONSULTA_CANCELADO = 3L;
    public static final Long STATUS_NOTIFICACAO_NAO_ENVIAR = 1L;

    public CancelarAgendamentoPorIdUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                               StatusConsultaDomainService statusConsultaDomainService,
                                               StatusNotificacaoDomainService statusNotificacaoDomainService,
                                               AgendamentoGateway agendamentoGateway,
                                               AgendamentoPublisher agendamentoPublisher) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.statusConsultaDomainService = statusConsultaDomainService;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
        this.agendamentoGateway = agendamentoGateway;
        this.agendamentoPublisher = agendamentoPublisher;
    }

    @Override
    public void cancelarAgendamentoPorId(Long id) {
        AgendamentoDomain agendamentoDomain = agendamentoDomainService.buscarAgendamentoDomainPorId(id);
        agendamentoDomain.setStatusConsultaDomain(statusConsultaDomainService.buscarStatusConsultaDomainPorId(STATUS_CONSULTA_CANCELADO));
        agendamentoDomain.setStatusNotificacaoDomain(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(STATUS_NOTIFICACAO_NAO_ENVIAR));
        AgendamentoDomain domainUpdate = agendamentoGateway.criarOuAtualizarAgendamento(agendamentoDomain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domainUpdate.getId(), domainUpdate.getCns(),
                domainUpdate.getStatusConsultaDomain(), domainUpdate.getStatusNotificacaoDomain(), OffsetDateTime.now()));
    }
}
