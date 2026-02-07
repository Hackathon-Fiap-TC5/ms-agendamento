package com.fiap.agendamento.infrastructure.config.usecase.agendamento;

import com.fiap.agendamento.application.usecase.agendamento.implementations.AtualizaStatusConsultaUseCaseImpl;
import com.fiap.agendamento.domain.domain.service.AgendamentoDomainService;
import com.fiap.agendamento.domain.domain.service.StatusConsultaDomainService;
import com.fiap.agendamento.infrastructure.queue.publisher.AgendamentoPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarStatusConsultaConfig {

    @Bean
    public AtualizaStatusConsultaUseCaseImpl registrarStatusConsultaUseCase(AgendamentoDomainService agendamentoDomainService,
                                                                            StatusConsultaDomainService statusConsultaDomainService,
                                                                            AgendamentoPublisher agendamentoPublisher) {
        return new AtualizaStatusConsultaUseCaseImpl(agendamentoDomainService, statusConsultaDomainService, agendamentoPublisher);
    }
}
