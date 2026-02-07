package com.fiap.agendamento.infrastructure.database.implementations;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.model.StatusConsultaDomain;
import com.fiap.agendamento.infrastructure.database.entities.StatusConsultaEntity;
import com.fiap.agendamento.infrastructure.database.mappers.StatusConsultaEntityMapper;
import com.fiap.agendamento.infrastructure.database.repositories.StatusConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusConsultaGatewayImpl implements StatusConsultaGateway {

    private final StatusConsultaRepository statusConsultaRepository;

    @Override
    public Optional<StatusConsultaDomain> buscarStatusConsultaPorId(Long id) {
        return statusConsultaRepository.findById(id)
                .map(StatusConsultaEntityMapper.INSTANCE::toStatusConsultaCriarDomain);
    }

    @Override
    public void removerStatus(StatusConsultaDomain statusConsultaDomain) {
        StatusConsultaEntity entity = StatusConsultaEntityMapper.INSTANCE.toStatusConsultaEntity(statusConsultaDomain);
        statusConsultaRepository.delete(entity);
    }

    @Override
    public void criarStatusConsulta(StatusConsultaDomain statusConsultaDomain) {
        StatusConsultaEntity entity = StatusConsultaEntityMapper.INSTANCE.toStatusConsultaCriarEntity(statusConsultaDomain);
        statusConsultaRepository.save(entity);
    }

    @Override
    public void atualizarStatusConsulta(StatusConsultaDomain statusConsultaDomain) {
        StatusConsultaEntity entity = StatusConsultaEntityMapper.INSTANCE.toStatusConsultaEntity(statusConsultaDomain);
        statusConsultaRepository.save(entity);
    }

    @Override
    public List<StatusConsultaDomain> buscarTodosOsStatus() {
        return statusConsultaRepository.findAll()
                .stream().map(
                        StatusConsultaEntityMapper.INSTANCE::toStatusConsultaCriarDomain
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<StatusConsultaDomain> buscarStatusConsultaPorDescricao(String status) {
        return statusConsultaRepository.findByStatusIgnoreCase(status)
                .map(StatusConsultaEntityMapper.INSTANCE::toStatusConsultaCriarDomain);
    }
}
