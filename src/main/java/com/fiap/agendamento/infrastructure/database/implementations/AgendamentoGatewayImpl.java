package com.fiap.agendamento.infrastructure.database.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.database.entities.AgendamentoEntity;
import com.fiap.agendamento.infrastructure.database.mappers.AgendamentoEntityMapper;
import com.fiap.agendamento.infrastructure.database.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendamentoGatewayImpl implements AgendamentoGateway {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public void criarOuAtualizarAgendamento(AgendamentoDomain agendamentoDomain) {
        AgendamentoEntity agendamentoEntity = AgendamentoEntityMapper.INSTANCE.toAgendamentoEntity(agendamentoDomain);
        agendamentoRepository.save(agendamentoEntity);
    }

    @Override
    public Optional<AgendamentoDomain> buscarAgendamentoPorId(Long idAgendamento) {
        return agendamentoRepository
                .findById(idAgendamento)
                .flatMap(entity ->
                        Optional.ofNullable(
                                AgendamentoEntityMapper.INSTANCE.toAgendamentoDomain(entity))
                );
    }
}
