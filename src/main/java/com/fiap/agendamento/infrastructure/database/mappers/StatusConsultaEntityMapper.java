package com.fiap.agendamento.infrastructure.database.mappers;

import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.infrastructure.database.entities.StatusConsultaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusConsultaEntityMapper {

    StatusConsultaEntityMapper INSTANCE = Mappers.getMapper(StatusConsultaEntityMapper.class);

    @Mapping(target = "id", ignore = true)
    StatusConsultaEntity toStatusConsultaCriarEntity(StatusConsultaDomain statusConsultaDomain);

    StatusConsultaDomain toStatusConsultaCriarDomain(StatusConsultaEntity statusConsultaEntity);

    List<StatusConsultaDomain> toListStatusConsultaDomain(List<StatusConsultaEntity> statusConsultaEntities);

    StatusConsultaEntity toStatusConsultaEntity(StatusConsultaDomain statusConsultaDomain);
}
