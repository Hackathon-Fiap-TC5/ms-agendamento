package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.usecase.status.notificacao.implementation.RemoverStatusNotificacaoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoverStatusNotificacaoConfig {

    @Bean
    public RemoverStatusNotificacaoUseCaseImpl removerStatusNotificacaoUseCase() {
        return new RemoverStatusNotificacaoUseCaseImpl();
    }
}
