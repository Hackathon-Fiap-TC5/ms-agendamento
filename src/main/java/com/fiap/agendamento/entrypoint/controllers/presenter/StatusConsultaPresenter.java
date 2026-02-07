package com.fiap.agendamento.entrypoint.controllers.presenter;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.entrypoint.controllers.mappers.StatusConsultaDtoMapper;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaResponseDto;

import java.util.List;

public class StatusConsultaPresenter {

    public static StatusConsultaDomain toStatusConsultaDomain(StatusConsultaRequestDto statusConsultaRequestDto) {
        return StatusConsultaDtoMapper.INSTANCE.toStatusConsultaDomain(statusConsultaRequestDto);
    }

    public static List<StatusConsultaResponseDto> toListStatusConsultaResponseDto(List<StatusConsultaDomain> listStatusConsultaDomains) {
        return StatusConsultaDtoMapper.INSTANCE.toListStatusConsultaResponseDto(listStatusConsultaDomains);
    }


    public static StatusConsultaDomain toStatusConsultaDomainAtualizar(AtualizarStatusConsultaRequestDto atualizarStatusConsultaRequestDto) {
        return StatusConsultaDtoMapper.INSTANCE.toStatusConsultaDomainAtualizar(atualizarStatusConsultaRequestDto);
    }
}
