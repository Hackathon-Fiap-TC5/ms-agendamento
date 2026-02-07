package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.implementation.RemoverStatusNotificacaoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoverStatusNotificacaoConfig {

    @Bean
    public RemoverStatusNotificacaoUseCaseImpl removerStatusNotificacaoUseCase(StatusNotificacaoGateway statusNotificacaoGateway,
                                                                               StatusNotificacaoDomainService statusNotificacaoDomainService) {
        return new RemoverStatusNotificacaoUseCaseImpl(statusNotificacaoGateway, statusNotificacaoDomainService);
    }
}