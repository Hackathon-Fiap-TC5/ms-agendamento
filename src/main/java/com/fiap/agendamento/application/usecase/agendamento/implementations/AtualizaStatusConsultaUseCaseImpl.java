package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.AtualizaStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.infrastructure.queue.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.queue.publisher.AgendamentoPublisher;

public class AtualizaStatusConsultaUseCaseImpl implements AtualizaStatusConsultaUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final StatusConsultaDomainService statusConsultaDomainService;
    private final AgendamentoPublisher agendamentoPublisher;

    public AtualizaStatusConsultaUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                             StatusConsultaDomainService statusConsultaDomainService,
                                             AgendamentoPublisher agendamentoPublisher) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.statusConsultaDomainService = statusConsultaDomainService;
        this.agendamentoPublisher = agendamentoPublisher;
    }

    @Override
    public void atualizaStatusConsulta(Long idAgendamento, Long idStatusConsulta) {
        AgendamentoDomain domain = agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
        StatusConsultaDomain statusConsultaDomain = statusConsultaDomainService.buscarStatusConsultaDomainPorId(idStatusConsulta);
        domain.setStatusConsultaDomain(statusConsultaDomain);
        agendamentoDomainService.criarOuAtualizarAgendamento(domain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domain.getCns(), domain.getStatusConsultaDomain(), domain.getStatusNotificacaoDomain()));
    }
}
