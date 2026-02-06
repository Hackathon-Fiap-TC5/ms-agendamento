package com.fiap.agendamento.application.usecase;

import com.fiap.agendamento.domain.model.AgendamentoDomain;

public interface RegistrarStatusConsultaUseCase {

    void registrarStatusConsulta(Long idAgendamento, AgendamentoDomain agendamentoDomain);
}
