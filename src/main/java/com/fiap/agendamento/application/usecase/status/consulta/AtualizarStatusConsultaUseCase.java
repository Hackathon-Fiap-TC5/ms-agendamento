package com.fiap.agendamento.application.usecase.status.consulta;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public interface AtualizarStatusConsultaUseCase {

    void atualizarStatusConsulta(Long id, StatusConsultaDomain domain);
}
