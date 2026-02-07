package com.fiap.agendamento.domain.domain.service;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

public interface StatusNotificacaoDomainService {

    StatusNotificacaoDomain buscarStatusNotificacaoDomainPorId(Long id);

    void validarExistenciaStatusPorDescricao(StatusNotificacaoDomain domain);
}
