package com.fiap.agendamento.application.usecase.agendamento;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

public interface RegistrarStatusConsultaUseCase {

    void registrarStatusConsulta(Long idAgendamento, AgendamentoDomain agendamentoDomain);
}
