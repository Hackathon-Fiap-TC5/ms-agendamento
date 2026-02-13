package com.fiap.agendamento.entrypoint.controllers.mappers;

import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamentoDomain.gen.model.FilaVivaResponseDto;
import com.fiap.agendamentoDomain.gen.model.PublicarFilaVivaRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FilaVivaDtoMapper {

    FilaVivaDtoMapper INSTANCE = Mappers.getMapper(FilaVivaDtoMapper.class);

    @Mapping(target = "idFilaViva", ignore = true)
    @Mapping(target = "dataEntrada", ignore = true)
    FilaVivaDomain toFilaVivaDomain(PublicarFilaVivaRequestDto publicarFilaVivaRequestDto);

    FilaVivaResponseDto toFilaVivaResponseDto(FilaVivaDomain domain);

    List<FilaVivaResponseDto> toListFilaVivaResponseDto(List<FilaVivaDomain> domains);
}
