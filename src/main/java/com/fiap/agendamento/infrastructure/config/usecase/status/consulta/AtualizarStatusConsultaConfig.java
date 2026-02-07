package com.fiap.agendamento.infrastructure.config.usecase.status.consulta;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.implementation.AtualizarStatusConsultaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarStatusConsultaConfig {

    @Bean
    public AtualizarStatusConsultaUseCaseImpl atualizarStatusConsultaUseCase(StatusConsultaGateway statusConsultaGateway) {
        return new AtualizarStatusConsultaUseCaseImpl(statusConsultaGateway);
    }
}
