package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.CriarAgendamentoUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.infrastructure.publisher.AgendamentoPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarAgendamentoConfig {

    @Bean
    public CriarAgendamentoUseCaseImpl criarAgendamentoUseCase(AgendamentoDomainService agendamentoDomainService,
                                                               StatusConsultaDomainService statusConsultaDomainService,
                                                               StatusNotificacaoDomainService statusNotificacaoDomainService,
                                                               AgendamentoPublisher agendamentoPublisher) {

        return new CriarAgendamentoUseCaseImpl(agendamentoDomainService, statusConsultaDomainService,
                statusNotificacaoDomainService, agendamentoPublisher);
    }
}
