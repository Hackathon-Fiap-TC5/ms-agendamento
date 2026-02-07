package com.fiap.agendamento.application.gateway;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;

import java.util.List;
import java.util.Optional;

public interface StatusNotificacaoGateway {
    void removerStatus(StatusNotificacaoDomain domain);

    Optional<StatusNotificacaoDomain> buscarStatusNotificacaoPorId(Long id);

    List<StatusNotificacaoDomain> buscarTodosOsStatus();

    void criarStatusNotificacao(StatusNotificacaoDomain domain);

    Optional<StatusNotificacaoDomain> buscarStatusNotificacaoPorDescricao(String status);

    void atualizarStatusNotificacao(StatusNotificacaoDomain statusConsultaDomain);
}
