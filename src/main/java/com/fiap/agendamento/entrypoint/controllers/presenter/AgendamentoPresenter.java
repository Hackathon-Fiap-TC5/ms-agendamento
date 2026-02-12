package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.AgendamentoDtoMapper;
import com.fiap.agendamento.infrastructure.producer.payload.AgendamentoMessageEvent;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import com.fiap.agendamentoDomain.gen.model.EventoAgendamentoMessagePayloadDto;

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

    public static StatusNotificacaoDomain toBuildStatusNotificacao(Long id) {
        StatusNotificacaoDomain statusNotificacao = new StatusNotificacaoDomain();
        statusNotificacao.setId(id);
        return statusNotificacao;
    }

    public static AgendamentoDomain toBuildAgendamentoDomainStatusConsulta(Long id) {
        AgendamentoDomain agendamentoDomain = new AgendamentoDomain();
        StatusConsultaDomain statusConsultaDomain = new StatusConsultaDomain();
        statusConsultaDomain.setId(id);
        agendamentoDomain.setStatusConsultaDomain(statusConsultaDomain);
        return agendamentoDomain;
    }

    public static EventoAgendamentoMessagePayloadDto toBuildPayloadPublisher(AgendamentoMessageEvent agendamentoMessageEvent){
        EventoAgendamentoMessagePayloadDto payload = new EventoAgendamentoMessagePayloadDto();
        payload.setIdAgendamento(agendamentoMessageEvent.getIdAgendamento());
        payload.setCns(agendamentoMessageEvent.getCns());
        payload.setStatusConsulta(agendamentoMessageEvent.getStatusConsultaDomain().getStatus());
        payload.setStatusNotificacao(agendamentoMessageEvent.getStatusNotificacaoDomain().getStatus());
        payload.setDataEvento(agendamentoMessageEvent.getDataEvento());
        return payload;
    }
}
