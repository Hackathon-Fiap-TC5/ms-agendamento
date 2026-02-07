package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.CriarAgendamentoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarAgendamentoConfig {

    @Bean
    public CriarAgendamentoUseCaseImpl criarAgendamentoUseCase(AgendamentoDomainService agendamentoDomainService) {
        return new CriarAgendamentoUseCaseImpl(agendamentoDomainService);
    }
}
