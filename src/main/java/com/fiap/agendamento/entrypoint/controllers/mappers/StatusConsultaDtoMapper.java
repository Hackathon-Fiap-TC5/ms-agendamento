package com.fiap.agendamento.entrypoint.controllers.mappers;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamentoDomain.gen.model.AtualizarStatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaRequestDto;
import com.fiap.agendamentoDomain.gen.model.StatusConsultaResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusConsultaDtoMapper {

    StatusConsultaDtoMapper INSTANCE = Mappers.getMapper(StatusConsultaDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    StatusConsultaDomain toStatusConsultaDomain(StatusConsultaRequestDto statusConsultaRequestDto);

    StatusConsultaResponseDto toStatusConsultaResponseDto(StatusConsultaDomain listStatusConsultaDomains);

    List<StatusConsultaResponseDto> toListStatusConsultaResponseDto(List<StatusConsultaDomain> listStatusConsultaDomains);

    @Mapping(target = "id", ignore = true)
    StatusConsultaDomain toStatusConsultaDomainAtualizar(AtualizarStatusConsultaRequestDto atualizarStatusConsultaRequestDto);
}
