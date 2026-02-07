package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.RemoverAgendamentoPorIdUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class RemoverAgendamentoPorIdUseCaseImpl implements RemoverAgendamentoPorIdUseCase {

    private final AgendamentoDomainService agendamentoDomainService;
    private final AgendamentoGateway agendamentoGateway;

    public RemoverAgendamentoPorIdUseCaseImpl(AgendamentoDomainService agendamentoDomainService,
                                              AgendamentoGateway agendamentoGateway) {
        this.agendamentoDomainService = agendamentoDomainService;
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void deletarAgendamentoPorId(Long id) {
        AgendamentoDomain agendamentoDomain = agendamentoDomainService.buscarAgendamentoDomainPorId(id);
        agendamentoGateway.deletar(agendamentoDomain);
    }
}
