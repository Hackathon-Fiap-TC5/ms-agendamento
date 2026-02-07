package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.usecase.agendamento.ConsultarAgendamentoPorIdUseCase;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class ConsultarAgendamentoPorIdUseCaseImpl implements ConsultarAgendamentoPorIdUseCase {

    private final AgendamentoDomainService agendamentoDomainService;

    public ConsultarAgendamentoPorIdUseCaseImpl(AgendamentoDomainService agendamentoDomainService) {
        this.agendamentoDomainService = agendamentoDomainService;
    }

    @Override
    public AgendamentoDomain consultarAgendamentoPorId(Long idAgendamento) {
        return agendamentoDomainService.buscarAgendamentoDomainPorId(idAgendamento);
    }
}
