package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.AtualizaStatusConsultaUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.publisher.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.publisher.AgendamentoPublisher;

import java.time.OffsetDateTime;

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
        domain.setStatusConsultaDomain(statusConsultaDomainService.buscarStatusConsultaDomainPorId(idStatusConsulta));
        AgendamentoDomain domainUpdated = agendamentoDomainService.criarOuAtualizarAgendamento(domain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domainUpdated.getId(), domainUpdated.getCns(),
                domainUpdated.getStatusConsultaDomain(), domainUpdated.getStatusNotificacaoDomain(), OffsetDateTime.now()));
    }
}
