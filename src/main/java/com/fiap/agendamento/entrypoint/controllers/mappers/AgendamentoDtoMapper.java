package com.fiap.agendamento.entrypoint.controllers.mappers;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamentoDomain.gen.model.AgendamentoResponseDto;
import com.fiap.agendamentoDomain.gen.model.CriarAgendamentoRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AgendamentoDtoMapper {

    AgendamentoDtoMapper INSTANCE = Mappers.getMapper(AgendamentoDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statusConsultaDomain", ignore = true)
    @Mapping(target = "statusNotificacaoDomain", ignore = true)
    AgendamentoDomain toAgendamentoDomain(CriarAgendamentoRequestDto criarAgendamentoRequestDto);

    @Mapping(target = "statusConsulta", source = "statusConsultaDomain")
    @Mapping(target = "statusNotificacao", source = "statusNotificacaoDomain")
    AgendamentoResponseDto toAgendamentoResponseDto(AgendamentoDomain domain);

    List<AgendamentoResponseDto> toListAgendamentoResponseDto(List<AgendamentoDomain> agendamentoDomains);
}
