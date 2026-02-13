package com.fiap.agendamento.infrastructure.config.usecase.filaviva;

import com.fiap.agendamento.application.gateway.FilaVivaGateway;
import com.fiap.agendamento.application.usecase.filaviva.implementations.ConsultarFilaVivaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarFilaVivaConfig {

    @Bean
    public ConsultarFilaVivaUseCaseImpl consultarFilaVivaUseCase(FilaVivaGateway filaVivaGateway) {
        return new ConsultarFilaVivaUseCaseImpl(filaVivaGateway);
    }
}
