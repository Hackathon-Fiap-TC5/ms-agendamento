package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.CriarAgendamentoUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.publisher.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.publisher.AgendamentoPublisher;

import java.time.OffsetDateTime;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final StatusConsultaDomainService statusConsultaDomainService;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;

    private final AgendamentoPublisher agendamentoPublisher;

    public static final Long STATUS_AGENDADO = 1L;
    public static final Long STATUS_NAO_ENVIADA = 1L;

    public CriarAgendamentoUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                       StatusConsultaDomainService statusConsultaDomainService,
                                       StatusNotificacaoDomainService statusNotificacaoDomainService,
                                       AgendamentoPublisher agendamentoPublisher) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.statusConsultaDomainService = statusConsultaDomainService;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
        this.agendamentoPublisher = agendamentoPublisher;
    }

    @Override
    public void criarAgendamento(AgendamentoDomain agendamentoDomain) {
        agendamentoDomain.setStatusConsultaDomain(statusConsultaDomainService.buscarStatusConsultaDomainPorId(STATUS_AGENDADO));
        agendamentoDomain.setStatusNotificacaoDomain(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(STATUS_NAO_ENVIADA));
        AgendamentoDomain domainCreated = agendamentoDomainService.criarOuAtualizarAgendamento(agendamentoDomain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domainCreated.getId(), domainCreated.getCns(),
                domainCreated.getStatusConsultaDomain(), domainCreated.getStatusNotificacaoDomain(), OffsetDateTime.now()));
    }
}
