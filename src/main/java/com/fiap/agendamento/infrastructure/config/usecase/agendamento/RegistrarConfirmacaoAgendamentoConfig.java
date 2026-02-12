package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.AtualizaStatusNotificacaoAgendamentoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.infrastructure.producer.AgendamentoPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarConfirmacaoAgendamentoConfig {

    @Bean
    public AtualizaStatusNotificacaoAgendamentoUseCaseImpl registrarConfirmacaoAgendamentoUseCase(AgendamentoDomainService agendamentoDomainService,
                                                                                                  StatusNotificacaoDomainService statusNotificacaoDomainService,
                                                                                                  AgendamentoPublisher agendamentoPublisher) {
        return new AtualizaStatusNotificacaoAgendamentoUseCaseImpl(agendamentoDomainService, agendamentoPublisher, statusNotificacaoDomainService);
    }
}
