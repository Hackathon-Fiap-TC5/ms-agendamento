package com.fiap.agendamento.infrastructure.config.domain.service;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.domain.domain.service.implementations.StatusConsultaDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusConsultaConfig {

    @Bean
    public StatusConsultaDomainServiceImpl statusConsultaDomainService(StatusConsultaGateway statusConsultaGateway) {
        return new StatusConsultaDomainServiceImpl(statusConsultaGateway);
    }
}
