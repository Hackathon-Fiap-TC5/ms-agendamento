package com.fiap.agendamento.domain.domain.service.implementations;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.exception.AgendamentoNaoEncontradoException;
import com.fiap.agendamento.domain.model.AgendamentoDomain;
import com.fiap.agendamento.infrastructure.producer.AgendamentoPublisher;

import java.util.List;

public class AgendamentoDomainServiceImpl implements AgendamentoDomainService {

    private final AgendamentoGateway agendamentoGateway;
    private final AgendamentoPublisher publisher;

    public AgendamentoDomainServiceImpl(AgendamentoGateway agendamentoGateway, AgendamentoPublisher publisher) {
        this.agendamentoGateway = agendamentoGateway;
        this.publisher = publisher;
    }

    @Override
    public AgendamentoDomain buscarAgendamentoDomainPorId(Long idAgendamento) {
        return agendamentoGateway.buscarAgendamentoPorId(idAgendamento)
                .orElseThrow(AgendamentoNaoEncontradoException::new);
    }

    @Override
    public List<AgendamentoDomain> buscarTodosAgendamentosPorCns(String cns) {
        return agendamentoGateway.buscarTodosAgendamentosPorCns(cns);
    }

    @Override
    public AgendamentoDomain criarOuAtualizarAgendamento(AgendamentoDomain domain) {
        return agendamentoGateway.criarOuAtualizarAgendamento(domain);
    }
}