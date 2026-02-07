package com.fiap.agendamento.application.usecase.agendamento;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

public interface RegistrarConfirmacaoAgendamentoUseCase {

    void registrarConfirmacaoAgendamento(Long idAgendamento, AgendamentoDomain agendamentoDomain);
}
