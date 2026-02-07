package com.fiap.agendamento.infrastructure.config.usecase.status.consulta;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.implementation.RemoverStatusConsultaUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoverStatusConsultaConfig {

    @Bean
    public RemoverStatusConsultaUseCaseImpl removerStatusConsultaUseCase(StatusConsultaGateway statusConsultaGateway,
                                                                         StatusConsultaDomainService statusConsultaDomainService) {
        return new RemoverStatusConsultaUseCaseImpl(statusConsultaGateway, statusConsultaDomainService);
    }
}
