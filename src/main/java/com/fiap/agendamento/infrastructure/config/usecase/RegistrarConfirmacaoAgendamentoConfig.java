package com.fiap.agendamento.infrastructure.config.usecase;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.implementation.RegistrarConfirmacaoAgendamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarConfirmacaoAgendamentoConfig {

    @Bean
    public RegistrarConfirmacaoAgendamentoUseCaseImpl registrarConfirmacaoAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new RegistrarConfirmacaoAgendamentoUseCaseImpl(agendamentoGateway);
    }
}
