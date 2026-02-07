package com.fiap.agendamento.infrastructure.database.mappers;

import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.database.entities.StatusNotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusNotificacaoEntityMapper {

    StatusNotificacaoEntityMapper INSTANCE = Mappers.getMapper(StatusNotificacaoEntityMapper.class);

    StatusNotificacaoEntity toStatusNotificacaoEntity(StatusNotificacaoDomain domain);

    StatusNotificacaoDomain toStatusNotificacaoDomain(StatusNotificacaoEntity statusNotificacaoEntity);

    @Mapping(target = "id", ignore = true)
    StatusNotificacaoEntity toStatusNotificacaoCriarEntity(StatusNotificacaoDomain domain);
}
