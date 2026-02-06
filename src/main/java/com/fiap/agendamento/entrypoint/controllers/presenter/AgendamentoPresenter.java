package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.AgendamentoDtoMapper;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;

public class AgendamentoPresenter {

    public static AgendamentoDomain toAgendamentoDomain(CriarAgendamentoRequestDto criarAgendamentoRequestDto){
        return AgendamentoDtoMapper.INSTANCE.toAgendamentoDomain(criarAgendamentoRequestDto);
    }
}
