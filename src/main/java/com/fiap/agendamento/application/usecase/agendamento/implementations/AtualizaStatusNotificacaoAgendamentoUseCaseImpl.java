package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.AtualizaStatusNotificacaoAgendamentoUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.producer.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.producer.AgendamentoPublisher;

import java.time.OffsetDateTime;

public class AtualizaStatusNotificacaoAgendamentoUseCaseImpl implements AtualizaStatusNotificacaoAgendamentoUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;
    private final AgendamentoPublisher agendamentoPublisher;

    public AtualizaStatusNotificacaoAgendamentoUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                                           AgendamentoPublisher agendamentoPublisher,
                                                           StatusNotificacaoDomainService statusNotificacaoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.agendamentoPublisher = agendamentoPublisher;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
    }

    @Override
    public void atualizaStatusNotificacao(Long idAgendamento, Long idStatusNotificacao) {
        AgendamentoDomain domain = agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
        domain.setStatusNotificacaoDomain(statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(idStatusNotificacao));
        AgendamentoDomain domainUpdated = agendamentoDomainService.criarOuAtualizarAgendamento(domain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domainUpdated.getId(), domainUpdated.getCns(),
                domainUpdated.getStatusConsultaDomain(), domainUpdated.getStatusNotificacaoDomain(), OffsetDateTime.now()));
    }
}
