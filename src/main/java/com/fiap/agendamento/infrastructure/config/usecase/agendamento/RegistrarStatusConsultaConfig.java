package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.RegistrarStatusConsultaUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarStatusConsultaConfig {

    @Bean
    public RegistrarStatusConsultaUseCaseImpl registrarStatusConsultaUseCase(AgendamentoDomainService agendamentoDomainService) {
        return new RegistrarStatusConsultaUseCaseImpl(agendamentoDomainService);
    }
}
