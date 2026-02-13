package com.fiap.agendamento.application.gateway;

import com.fiap.agendamento.domain.model.FilaVivaDomain;

import java.util.List;

public interface FilaVivaGateway {

    void salvar(FilaVivaDomain domain);

    List<FilaVivaDomain> consultarFilaViva();
}
