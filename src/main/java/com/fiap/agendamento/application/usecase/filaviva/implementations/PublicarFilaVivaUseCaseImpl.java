package com.fiap.agendamento.application.usecase.filaviva.implementations;

import com.fiap.agendamento.application.gateway.FilaVivaGateway;
import com.fiap.agendamento.application.usecase.filaviva.PublicarFilaVivaUseCase;
import com.fiap.agendamento.domain.model.FilaVivaDomain;
import org.springframework.scheduling.annotation.Async;

import java.time.OffsetDateTime;

public class PublicarFilaVivaUseCaseImpl implements PublicarFilaVivaUseCase {

    private final FilaVivaGateway filaVivaGateway;

    public PublicarFilaVivaUseCaseImpl(FilaVivaGateway filaVivaGateway) {
        this.filaVivaGateway = filaVivaGateway;
    }

    @Async
    @Override
    public void publicarFilaViva(FilaVivaDomain domain) {
        domain.setDataEntrada(OffsetDateTime.now());
        filaVivaGateway.salvar(domain);
    }
}
