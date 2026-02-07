package com.fiap.agendamento.infrastructure.database.mappers;

import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.database.entities.AgendamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AgendamentoEntityMapper {

    AgendamentoEntityMapper INSTANCE = Mappers.getMapper(AgendamentoEntityMapper.class);

    @Mapping(target = "statusConsulta", source = "statusConsultaDomain")
    @Mapping(target = "statusNotificacao", source = "statusNotificacaoDomain")
    AgendamentoEntity toAgendamentoEntity(AgendamentoDomain domain);

    @Mapping(target = "statusConsultaDomain", source = "statusConsulta")
    @Mapping(target = "statusNotificacaoDomain", source = "statusNotificacao")
    AgendamentoDomain toAgendamentoDomain(AgendamentoEntity entity);

    List<AgendamentoDomain> toListAgendamentoDomain(List<AgendamentoEntity> listAgendamentoEntity);

}
