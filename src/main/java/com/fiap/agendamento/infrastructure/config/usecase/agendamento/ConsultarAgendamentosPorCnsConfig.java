package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementation.ConsultarAgendamentosPorCnsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarAgendamentosPorCnsConfig {

    @Bean
    public ConsultarAgendamentosPorCnsUseCaseImpl consultarAgendamentosPorCnsUseCase(AgendamentoGateway agendamentoGateway) {
        return new ConsultarAgendamentosPorCnsUseCaseImpl(agendamentoGateway);
    }
}
