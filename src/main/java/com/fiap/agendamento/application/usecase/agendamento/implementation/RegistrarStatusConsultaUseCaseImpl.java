package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarStatusConsultaUseCase;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class RegistrarStatusConsultaUseCaseImpl implements RegistrarStatusConsultaUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public RegistrarStatusConsultaUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void registrarStatusConsulta(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);

        domain.setStatusConsultaEnum(agendamentoDomain.getStatusConsultaEnum());
        agendamentoGateway.criarOuAtualizarAgendamento(domain);
    }
}
