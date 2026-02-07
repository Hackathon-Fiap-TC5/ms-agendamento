package com.fiap.agendamento.application.usecase.status.notificacao;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public interface CriarStatusNotificacaoUseCase {

    void criarStatusNotificacao(StatusNotificacaoDomain domain);
}
