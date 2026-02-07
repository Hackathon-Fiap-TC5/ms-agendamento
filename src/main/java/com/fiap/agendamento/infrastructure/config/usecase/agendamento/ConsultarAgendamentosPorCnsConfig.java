package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.ConsultarAgendamentosPorCnsUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarAgendamentosPorCnsConfig {

    @Bean
    public ConsultarAgendamentosPorCnsUseCaseImpl consultarAgendamentosPorCnsUseCase(AgendamentoDomainService agendamentoDomainService) {
        return new ConsultarAgendamentosPorCnsUseCaseImpl(agendamentoDomainService);
    }
}
