package com.fiap.agendamento.infrastructure.config.domain.service;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.domain.domain.service.implementations.StatusNotificacaoDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusNotificacaoConfig {

    @Bean
    public StatusNotificacaoDomainServiceImpl statusNotificacaoDomainService(StatusNotificacaoGateway statusNotificacaoGateway) {
        return new StatusNotificacaoDomainServiceImpl(statusNotificacaoGateway);
    }
}
