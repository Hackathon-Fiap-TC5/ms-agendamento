package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.StatusConsultaDtoMapper;
import com.fiap.agendamento.entrypoint.controllers.mappers.StatusNotificacaoDtoMapper;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoResponseDto;

import java.util.List;

public class StatusNotificacaoPresenter{

    public static List<StatusNotificacaoResponseDto> toListStatusNotificacaoResponseDto(List<StatusNotificacaoDomain> listStatusNotificacaoDomains) {
        return StatusNotificacaoDtoMapper.INSTANCE.toListStatusNotificacaoResponseDto(listStatusNotificacaoDomains);
    }

    public static StatusNotificacaoDomain toStatusNotificacaoDomain(StatusNotificacaoRequestDto statusNotificacaoRequestDto) {
        return StatusNotificacaoDtoMapper.INSTANCE.toStatusNotificacaoDomain(statusNotificacaoRequestDto);
    }

    public static StatusNotificacaoDomain toStatusNotificacaoDomainAtualizar(AtualizarStatusNotificacaoRequestDto atualizarStatusNotificacaoRequestDto) {
        return StatusNotificacaoDtoMapper.INSTANCE.toStatusNotificacaoDomainAtualizar(atualizarStatusNotificacaoRequestDto);
    }
}
