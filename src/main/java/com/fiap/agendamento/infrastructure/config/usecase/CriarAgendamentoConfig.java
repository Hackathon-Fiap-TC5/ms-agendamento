package com.fiap.agendamento.infrastructure.config.usecase;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.implementation.CriarAgendamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarAgendamentoConfig {

    @Bean
    public CriarAgendamentoUseCaseImpl criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }
}
