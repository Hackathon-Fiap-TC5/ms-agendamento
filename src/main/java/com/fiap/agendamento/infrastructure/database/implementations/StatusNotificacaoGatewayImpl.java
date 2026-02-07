package com.fiap.agendamento.infrastructure.database.implementations;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.model.StatusNotificacaoDomain;
import com.fiap.agendamento.infrastructure.database.entities.StatusNotificacaoEntity;
import com.fiap.agendamento.infrastructure.database.mappers.StatusNotificacaoEntityMapper;
import com.fiap.agendamento.infrastructure.database.repositories.StatusNotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusNotificacaoGatewayImpl implements StatusNotificacaoGateway {

    private final StatusNotificacaoRepository statusNotificacaoRepository;


    @Override
    public void removerStatus(StatusNotificacaoDomain domain) {
        StatusNotificacaoEntity entity = StatusNotificacaoEntityMapper.INSTANCE.toStatusNotificacaoEntity(domain);
        statusNotificacaoRepository.delete(entity);
    }

    @Override
    public Optional<StatusNotificacaoDomain> buscarStatusNotificacaoPorId(Long id) {
        return statusNotificacaoRepository.findById(id)
                .map(StatusNotificacaoEntityMapper.INSTANCE::toStatusNotificacaoDomain);
    }

    @Override
    public List<StatusNotificacaoDomain> buscarTodosOsStatus() {
        return statusNotificacaoRepository.findAll()
                .stream().map(
                        StatusNotificacaoEntityMapper.INSTANCE::toStatusNotificacaoDomain
                ).collect(Collectors.toList());
    }

    @Override
    public void criarStatusNotificacao(StatusNotificacaoDomain domain) {
        StatusNotificacaoEntity entity = StatusNotificacaoEntityMapper.INSTANCE.toStatusNotificacaoCriarEntity(domain);
        statusNotificacaoRepository.save(entity);
    }

    @Override
    public Optional<StatusNotificacaoDomain> buscarStatusNotificacaoPorDescricao(String status) {
        return statusNotificacaoRepository.findByStatusIgnoreCase(status)
                .map(StatusNotificacaoEntityMapper.INSTANCE::toStatusNotificacaoDomain);
    }

    @Override
    public void atualizarStatusNotificacao(StatusNotificacaoDomain statusConsultaDomain) {
        StatusNotificacaoEntity entity = StatusNotificacaoEntityMapper.INSTANCE.toStatusNotificacaoEntity(statusConsultaDomain);
        statusNotificacaoRepository.save(entity);
    }
}
