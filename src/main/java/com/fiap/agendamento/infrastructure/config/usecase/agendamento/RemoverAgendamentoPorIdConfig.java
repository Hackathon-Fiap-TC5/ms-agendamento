package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementations.RemoverAgendamentoPorIdUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoverAgendamentoPorIdConfig {

    @Bean
    public RemoverAgendamentoPorIdUseCaseImpl removerAgendamentoPorIdUseCase(AgendamentoDomainService agendamentoDomainService,
                                                                             AgendamentoGateway agendamentoGateway) {
        return new RemoverAgendamentoPorIdUseCaseImpl(agendamentoDomainService, agendamentoGateway);
    }
}
