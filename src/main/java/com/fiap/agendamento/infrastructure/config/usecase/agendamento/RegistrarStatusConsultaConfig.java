package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementation.RegistrarStatusConsultaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarStatusConsultaConfig {

    @Bean
    public RegistrarStatusConsultaUseCaseImpl registrarStatusConsultaUseCase(AgendamentoGateway agendamentoGateway) {
        return new RegistrarStatusConsultaUseCaseImpl(agendamentoGateway);
    }
}
