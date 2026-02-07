package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.queue.payload.AgendamentoMessageEvent;
import com.fiap.agendamento.infrastructure.queue.publisher.AgendamentoPublisher;

public class RegistrarConfirmacaoAgendamentoUseCaseImpl implements RegistrarConfirmacaoAgendamentoUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final StatusNotificacaoDomainService statusNotificacaoDomainService;
    private final AgendamentoPublisher agendamentoPublisher;

    public RegistrarConfirmacaoAgendamentoUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                                      AgendamentoPublisher agendamentoPublisher,
                                                      StatusNotificacaoDomainService statusNotificacaoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.agendamentoPublisher = agendamentoPublisher;
        this.statusNotificacaoDomainService = statusNotificacaoDomainService;
    }

    @Override
    public void registrarConfirmacaoAgendamento(Long idAgendamento, Long idStatusNotificacao) {
        AgendamentoDomain domain = agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
        StatusNotificacaoDomain status = statusNotificacaoDomainService.buscarStatusNotificacaoDomainPorId(idStatusNotificacao);
        domain.setStatusNotificacaoDomain(status);
        agendamentoDomainService.criarOuAtualizarAgendamento(domain);

        agendamentoPublisher.publisher(new AgendamentoMessageEvent(domain.getCns(), domain.getStatusConsultaDomain(), domain.getStatusNotificacaoDomain()));
    }
}
