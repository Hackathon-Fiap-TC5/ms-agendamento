package com.fiap.agendamento.application.usecase.status.consulta;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;

import java.util.List;

public interface ListarStatusConsultaUseCase {

    List<StatusConsultaDomain> listarStatusConsultas();
}
