package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementations.IntegraContextoAgendamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegraContextoAgendamento {

    @Bean
    public IntegraContextoAgendamentoUseCaseImpl integraContextoAgendamentoUseCase(AgendamentoGateway agendamentoGateway){
        return new IntegraContextoAgendamentoUseCaseImpl(agendamentoGateway);
    }
}
