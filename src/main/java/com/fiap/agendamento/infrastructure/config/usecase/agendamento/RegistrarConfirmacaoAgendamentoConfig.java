package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.RegistrarConfirmacaoAgendamentoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarConfirmacaoAgendamentoConfig {

    @Bean
    public RegistrarConfirmacaoAgendamentoUseCaseImpl registrarConfirmacaoAgendamentoUseCase(AgendamentoDomainService agendamentoDomainService) {
        return new RegistrarConfirmacaoAgendamentoUseCaseImpl(agendamentoDomainService);
    }
}
