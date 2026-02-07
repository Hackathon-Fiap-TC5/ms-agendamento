package com.fiap.agendamento.application.usecase.status.notificacao;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

import java.util.List;

public interface ListarStatusNotificacaoUseCase {

    List<StatusNotificacaoDomain> listarStatusConsultas();
}
