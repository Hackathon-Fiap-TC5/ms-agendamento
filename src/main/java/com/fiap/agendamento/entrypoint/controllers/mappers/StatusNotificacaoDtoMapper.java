package com.fiap.agendamento.entrypoint.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusNotificacaoDtoMapper {

    StatusNotificacaoDtoMapper INSTANCE = Mappers.getMapper(StatusNotificacaoDtoMapper.class);


}
