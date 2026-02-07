package com.fiap.agendamento.domain.domain.service;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;

public interface StatusConsultaDomainService {

    StatusConsultaDomain buscarStatusConsultaDomainPorId(Long id);

//    StatusConsultaDomain buscaStatusConsultaPorNome(String status);

    void validarExistenciaStatusPorDescricao(StatusConsultaDomain domain);
}