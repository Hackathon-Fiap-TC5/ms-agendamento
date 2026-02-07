package com.fiap.agendamento.entrypoint.controllers.mappers;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusNotificacaoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusNotificacaoDtoMapper {

    StatusNotificacaoDtoMapper INSTANCE = Mappers.getMapper(StatusNotificacaoDtoMapper.class);

    StatusNotificacaoResponseDto toStatusNotificacaoResponseDto(StatusNotificacaoDomain statusNotificacaoDomain);

    List<StatusNotificacaoResponseDto> toListStatusNotificacaoResponseDto(List<StatusNotificacaoDomain> listStatusNotificacaoDomains);

    @Mapping(target = "id", ignore = true)
    StatusNotificacaoDomain toStatusNotificacaoDomain(StatusNotificacaoRequestDto statusNotificacaoRequestDto);

    @Mapping(target = "id", ignore = true)
    StatusNotificacaoDomain toStatusNotificacaoDomainAtualizar(AtualizarStatusNotificacaoRequestDto atualizarStatusNotificacaoRequestDto);
}
