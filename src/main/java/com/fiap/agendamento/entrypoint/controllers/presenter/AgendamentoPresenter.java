package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.AgendamentoDtoMapper;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;

import java.util.List;

public class AgendamentoPresenter {

    public static AgendamentoDomain toAgendamentoDomain(CriarAgendamentoRequestDto criarAgendamentoRequestDto){
        return AgendamentoDtoMapper.INSTANCE.toAgendamentoDomain(criarAgendamentoRequestDto);
    }

    public static AgendamentoResponseDto toAgendamentoResponseDto(AgendamentoDomain domain) {
        return AgendamentoDtoMapper.INSTANCE.toAgendamentoResponseDto(domain);
    }

    public static List<AgendamentoResponseDto> toListAgendamentoResponseDto(List<AgendamentoDomain> agendamentoDomains) {
        return AgendamentoDtoMapper.INSTANCE.toListAgendamentoResponseDto(agendamentoDomains);
    }

    public static AgendamentoDomain toBuildAgendamentoDomainStatusNotificacao(Long id) {
        AgendamentoDomain agendamentoDomain = new AgendamentoDomain();
        StatusNotificacaoDomain statusNotificacaoDomain = new StatusNotificacaoDomain();
        statusNotificacaoDomain.setId(id);
        agendamentoDomain.setStatusNotificacaoDomain(statusNotificacaoDomain);
        return agendamentoDomain;
    }

    public static AgendamentoDomain toBuildAgendamentoDomainStatusConsulta(Long id) {
        AgendamentoDomain agendamentoDomain = new AgendamentoDomain();
        StatusConsultaDomain statusConsultaDomain = new StatusConsultaDomain();
        statusConsultaDomain.setId(id);
        agendamentoDomain.setStatusConsultaDomain(statusConsultaDomain);
        return agendamentoDomain;
    }
}
