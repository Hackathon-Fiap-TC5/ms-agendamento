package com.fiap.agendamento.infrastructure.config.domain.service;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.domain.domain.service.implementations.AgendamentoDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgendamentoConfig {

    @Bean
    public AgendamentoDomainServiceImpl agendamentoDomainService(AgendamentoGateway agendamentoGateway) {
        return new AgendamentoDomainServiceImpl(agendamentoGateway);
    }
}
