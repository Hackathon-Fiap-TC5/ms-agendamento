package com.fiap.agendamento.application.usecase.status.consulta.implementation;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.ListarStatusConsultaUseCase;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;

import java.util.List;

public class ListarStatusConsultaUseCaseImpl implements ListarStatusConsultaUseCase {

    private final StatusConsultaGateway statusConsultaGateway;

    public ListarStatusConsultaUseCaseImpl(StatusConsultaGateway statusConsultaGateway) {
        this.statusConsultaGateway = statusConsultaGateway;
    }

    @Override
    public List<StatusConsultaDomain> listarStatusConsultas() {
        return statusConsultaGateway.buscarTodosOsStatus();
    }
}
