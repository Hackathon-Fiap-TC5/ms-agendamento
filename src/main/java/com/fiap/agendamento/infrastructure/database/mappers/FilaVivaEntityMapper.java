package com.fiap.agendamento.infrastructure.database.mappers;

import com.fiap.agendamento.domain.model.FilaVivaDomain;
import com.fiap.agendamento.infrastructure.database.entities.FilaVivaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FilaVivaEntityMapper {

    FilaVivaEntityMapper INSTANCE = Mappers.getMapper(FilaVivaEntityMapper.class);

    FilaVivaEntity toFilaVivaEntity(FilaVivaDomain domain);

    FilaVivaDomain toFilaVivaDomain(FilaVivaEntity filaVivaEntity);
}
