package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.usecase.status.notificacao.implementation.ListarStatusNotificacaoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListarStatusNotificacaoConfig {

    @Bean
    public ListarStatusNotificacaoUseCaseImpl listarStatusNotificacaoUseCase() {
        return new ListarStatusNotificacaoUseCaseImpl();
    }
}
