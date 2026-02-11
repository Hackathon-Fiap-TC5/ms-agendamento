package com.fiap.agendamento.application.usecase.agendamento.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.IntegraContextoAgendamentoUseCase;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.EventoComparecimentoMessageDomain;

public class IntegraContextoAgendamentoUseCaseImpl implements IntegraContextoAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public IntegraContextoAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void integraContexto(EventoComparecimentoMessageDomain eventoComparecimento) {
        Long idAgendamento = eventoComparecimento.getIdAgendamento();

        AgendamentoDomain agendamento = agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(() -> new IllegalStateException("Agendamento não encontrado para o id: " + idAgendamento));

        aplicarContextoComparecimento(agendamento, eventoComparecimento);
        agendamentoGateway.criarOuAtualizarAgendamento(agendamento);
    }

    private void aplicarContextoComparecimento(AgendamentoDomain agendamento,
                                               EventoComparecimentoMessageDomain evento) {

        agendamento.setSugestaoConduta(evento.getSugestaoConduta());
        agendamento.setIccScore(evento.getIccScore());
        agendamento.setJustificativa(evento.getJustificativa());
    }
}
