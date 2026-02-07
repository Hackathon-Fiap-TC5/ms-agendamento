package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementation.ConsultarAgendamentoPorIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarAgendamentoPorIdConfig {

    @Bean
    public ConsultarAgendamentoPorIdUseCaseImpl consultarAgendamentoPorId(AgendamentoGateway agendamentoGateway) {
        return new ConsultarAgendamentoPorIdUseCaseImpl(agendamentoGateway);
    }
}
