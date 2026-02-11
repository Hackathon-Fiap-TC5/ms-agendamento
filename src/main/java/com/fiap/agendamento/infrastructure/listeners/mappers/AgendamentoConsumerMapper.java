package com.fiap.agendamento.infrastructure.listeners.mappers;

import com.fiap.agendamento.domain.model.EventoComparecimentoMessageDomain;
import com.fiap.agendamentoDomain.gen.model.EventoComparecimentoMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AgendamentoConsumerMapper {

    AgendamentoConsumerMapper INSTANCE = Mappers.getMapper(AgendamentoConsumerMapper.class);

    EventoComparecimentoMessageDomain toEventoComparecimentoMessageDomain(EventoComparecimentoMessageDto eventoComparecimentoMessageDto);

    EventoComparecimentoMessageDto toEventoComparecimentoMessageDto(EventoComparecimentoMessageDto dto);
}
