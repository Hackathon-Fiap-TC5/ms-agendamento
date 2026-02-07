package com.fiap.agendamento.infrastructure.config.usecase.status.consulta;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.implementation.ListarStatusConsultaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListarStatusConsultaConfig {

    @Bean
    public ListarStatusConsultaUseCaseImpl listarStatusConsultaUseCase(StatusConsultaGateway statusConsultaGateway) {
        return new ListarStatusConsultaUseCaseImpl(statusConsultaGateway);
    }
}
