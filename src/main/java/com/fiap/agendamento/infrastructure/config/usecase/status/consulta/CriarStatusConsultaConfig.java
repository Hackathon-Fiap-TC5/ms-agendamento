package com.fiap.agendamento.infrastructure.config.usecase.status.consulta;

import com.fiap.agendamento.application.gateway.StatusConsultaGateway;
import com.fiap.agendamento.application.usecase.status.consulta.implementation.CriarStatusConsultaUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarStatusConsultaConfig {

    @Bean
    public CriarStatusConsultaUseCaseImpl criarStatusConsultaUseCase(StatusConsultaGateway statusConsultaGateway,
                                                                     StatusConsultaDomainService statusConsultaDomainService) {
        return new CriarStatusConsultaUseCaseImpl(statusConsultaGateway, statusConsultaDomainService);
    }
}
