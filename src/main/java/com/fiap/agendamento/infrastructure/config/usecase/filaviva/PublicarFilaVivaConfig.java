package com.fiap.agendamento.infrastructure.config.usecase.filaviva;

import com.fiap.agendamento.application.gateway.FilaVivaGateway;
import com.fiap.agendamento.application.usecase.filaviva.implementations.PublicarFilaVivaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class PublicarFilaVivaConfig {

    @Bean
    public PublicarFilaVivaUseCaseImpl publicarFilaVivaUseCase(FilaVivaGateway filaVivaGateway) {
        return new PublicarFilaVivaUseCaseImpl(filaVivaGateway);
    }
}
