package com.fiap.agendamento.application.usecase;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

public interface RegistrarConfirmacaoAgendamentoUseCase {

    void registrarConfirmacaoAgendamento(Long idAgendamento, AgendamentoDomain agendamentoDomain);
}
