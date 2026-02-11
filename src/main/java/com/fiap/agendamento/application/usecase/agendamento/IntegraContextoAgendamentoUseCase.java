package com.fiap.agendamento.application.usecase.agendamento;

import com.fiap.agendamento.domain.model.EventoComparecimentoMessageDomain;

public interface IntegraContextoAgendamentoUseCase {

    void integraContexto(EventoComparecimentoMessageDomain eventoComparecimentoMessageDomain);
}
