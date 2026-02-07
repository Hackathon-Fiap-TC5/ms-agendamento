package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.usecase.status.notificacao.implementation.AtualizarStatusNotificacaoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarStatusNotificacaoConfig {

    @Bean
    public AtualizarStatusNotificacaoUseCaseImpl atualizarStatusNotificacaoUseCase() {
        return new AtualizarStatusNotificacaoUseCaseImpl();
    }
}
