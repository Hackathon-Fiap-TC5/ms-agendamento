package com.fiap.agendamento.infrastructure.database.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusNotificacaoEntityMapper {

    StatusNotificacaoEntityMapper INSTANCE = Mappers.getMapper(StatusNotificacaoEntityMapper.class);


}
