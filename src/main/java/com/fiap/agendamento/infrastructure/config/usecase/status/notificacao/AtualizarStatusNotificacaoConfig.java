package com.fiap.agendamento.infrastructure.config.usecase.status.notificacao;

import com.fiap.agendamento.application.gateway.StatusNotificacaoGateway;
import com.fiap.agendamento.application.usecase.status.notificacao.implementation.AtualizarStatusNotificacaoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarStatusNotificacaoConfig {

    @Bean
    public AtualizarStatusNotificacaoUseCaseImpl atualizarStatusNotificacaoUseCase(StatusNotificacaoGateway statusNotificacaoGateway,
                                                                                   StatusNotificacaoDomainService statusNotificacaoDomainService) {
        return new AtualizarStatusNotificacaoUseCaseImpl(statusNotificacaoGateway, statusNotificacaoDomainService);
    }
}
