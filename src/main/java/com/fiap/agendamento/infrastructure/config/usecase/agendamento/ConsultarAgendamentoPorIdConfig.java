package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.ConsultarAgendamentoPorIdUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarAgendamentoPorIdConfig {

    @Bean
    public ConsultarAgendamentoPorIdUseCaseImpl consultarAgendamentoPorId(AgendamentoDomainService agendamentoDomainService) {
        return new ConsultarAgendamentoPorIdUseCaseImpl(agendamentoDomainService);
    }
}
