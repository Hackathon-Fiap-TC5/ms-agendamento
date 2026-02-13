package com.fiap.agendamento.application.usecase.filaviva.implementations;

import com.fiap.agendamento.application.gateway.FilaVivaGateway;
import com.fiap.agendamento.application.usecase.filaviva.ConsultarFilaVivaUseCase;
import com.fiap.agendamento.domain.model.FilaVivaDomain;

import java.util.List;

public class ConsultarFilaVivaUseCaseImpl implements ConsultarFilaVivaUseCase {

    private final FilaVivaGateway filaVivaGateway;

    public ConsultarFilaVivaUseCaseImpl(FilaVivaGateway filaVivaGateway) {
        this.filaVivaGateway = filaVivaGateway;
    }

    @Override
    public List<FilaVivaDomain> consultarFilaViva() {
        return filaVivaGateway.consultarFilaViva();
    }
}
