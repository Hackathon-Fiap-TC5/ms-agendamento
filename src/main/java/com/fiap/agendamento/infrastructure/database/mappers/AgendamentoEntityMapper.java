package com.fiap.agendamento.infrastructure.database.mappers;

import com.fiap.agendamento.domain.enums.StatusConsultaEnum;
import com.fiap.agendamento.domain.enums.StatusNotificacaoEnum;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.database.entities.AgendamentoEntity;
import com.fiap.agendamento.infrastructure.database.entities.StatusConsultaEntity;
import com.fiap.agendamento.infrastructure.database.entities.StatusNotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AgendamentoEntityMapper {

    AgendamentoEntityMapper INSTANCE = Mappers.getMapper(AgendamentoEntityMapper.class);

    @Mapping(source = "statusNotificacaoEnum", target = "statusNotificacao")
    @Mapping(source = "statusConsultaEnum", target = "statusConsulta")
    AgendamentoEntity toAgendamentoEntity(AgendamentoDomain domain);

    @Mapping(source = "statusNotificacao", target = "statusNotificacaoEnum")
    @Mapping(source = "statusConsulta", target = "statusConsultaEnum")
    AgendamentoDomain toAgendamentoDomain(AgendamentoEntity entity);

    default StatusNotificacaoEntity map(StatusNotificacaoEnum enumValue) {
        if (enumValue == null) return null;

        StatusNotificacaoEntity entity = new StatusNotificacaoEntity();
        entity.setId((long) enumValue.getId());
        entity.setStatus(enumValue.getStatus());
        return entity;
    }

    default StatusNotificacaoEnum map(StatusNotificacaoEntity entity) {
        if (entity == null) return null;

        return StatusNotificacaoEnum.fromId(entity.getId().intValue());
    }

    default StatusConsultaEntity map(StatusConsultaEnum enumValue) {
        if (enumValue == null) return null;

        StatusConsultaEntity entity = new StatusConsultaEntity();
        entity.setId((long) enumValue.getId());
        entity.setStatus(enumValue.getStatus());
        return entity;
    }

    default StatusConsultaEnum map(StatusConsultaEntity entity) {
        if (entity == null) return null;

        return StatusConsultaEnum.fromId(entity.getId().intValue());
    }
}
