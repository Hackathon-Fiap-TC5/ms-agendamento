package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.gateway.AgendamentoGateway;
import com.fiap.agendamento.application.usecase.agendamento.implementations.CancelarAgendamentoPorIdUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.domain.domain.service.StatusNotificacaoDomainService;
import com.fiap.agendamento.infrastructure.producer.AgendamentoPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoverAgendamentoPorIdConfig {

    @Bean
    public CancelarAgendamentoPorIdUseCaseImpl removerAgendamentoPorIdUseCase(AgendamentoDomainService agendamentoDomainService,
                                                                              StatusConsultaDomainService statusConsultaDomainService,
                                                                              StatusNotificacaoDomainService statusNotificacaoDomainService,
                                                                              AgendamentoGateway agendamentoGateway,
                                                                              AgendamentoPublisher agendamentoPublisher) {

        return new CancelarAgendamentoPorIdUseCaseImpl(agendamentoDomainService, statusConsultaDomainService,
                statusNotificacaoDomainService, agendamentoGateway, agendamentoPublisher);
    }
}
