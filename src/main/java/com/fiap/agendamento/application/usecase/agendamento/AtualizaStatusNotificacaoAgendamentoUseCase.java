package com.fiap.agendamento.application.usecase.agendamento;


public interface RegistrarConfirmacaoAgendamentoUseCase {

    void registrarConfirmacaoAgendamento(Long idAgendamento, Long idStatusNotificao);
}
