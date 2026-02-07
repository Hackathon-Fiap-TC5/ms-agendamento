package com.fiap.agendamento.application.usecase.agendamento.implementation;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.RegistrarConfirmacaoAgendamentoUseCase;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;

public class RegistrarConfirmacaoAgendamentoUseCaseImpl implements RegistrarConfirmacaoAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public RegistrarConfirmacaoAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void registrarConfirmacaoAgendamento(Long idAgendamento, AgendamentoDomain agendamentoDomain) {
        AgendamentoDomain domain = agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);

        domain.setStatusNotificacaoEnum(agendamentoDomain.getStatusNotificacaoEnum());
        agendamentoGateway.criarOuAtualizarAgendamento(domain);
    }
}
