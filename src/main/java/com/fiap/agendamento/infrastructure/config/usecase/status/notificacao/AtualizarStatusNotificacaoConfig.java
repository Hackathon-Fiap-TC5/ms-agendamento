package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.implementation.AtualizarStatusNotificacaoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarStatusNotificacaoConfig {

    @Bean
    public AtualizarStatusNotificacaoUseCaseImpl atualizarStatusNotificacaoUseCase(StatusNotificacaoGateway statusNotificacaoGateway) {
        return new AtualizarStatusNotificacaoUseCaseImpl(statusNotificacaoGateway);
    }
}
