package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.usecase.status.notificacao.implementation.CriarStatusNotificacaoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarStatusNotificacaoConfig {

    @Bean
    public CriarStatusNotificacaoUseCaseImpl criarStatusNotificacaoUseCase() {
        return new CriarStatusNotificacaoUseCaseImpl();
    }
}
