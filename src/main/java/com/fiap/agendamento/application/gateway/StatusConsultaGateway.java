package com.fiap.agendamento.application.gateway;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;

import java.util.List;
import java.util.Optional;

public interface StatusConsultaGateway {
    Optional<StatusConsultaDomain> buscarStatusConsultaPorId(Long id);

    void removerStatus(StatusConsultaDomain statusConsultaDomain);

    void criarStatusConsulta(StatusConsultaDomain statusConsultaDomain);

    void atualizarStatusConsulta(StatusConsultaDomain statusConsultaDomain);

    List<StatusConsultaDomain> buscarTodosOsStatus();

    Optional<StatusConsultaDomain> buscarStatusConsultaPorDescricao(String status);
}
