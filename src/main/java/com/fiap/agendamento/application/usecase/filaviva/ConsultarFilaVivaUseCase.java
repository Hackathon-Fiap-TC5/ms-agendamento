package com.fiap.agendamento.application.usecase.filaviva;

import com.fiap.agendamento.domain.model.FilaVivaDomain;

import java.util.List;

public interface ConsultarFilaVivaUseCase {

    List<FilaVivaDomain> consultarFilaViva();
}
