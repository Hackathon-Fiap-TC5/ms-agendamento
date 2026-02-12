package com.fiap.agendamento.entrypoint.consumer;

import com.fiap.agendamento.application.usecase.agendamento.IntegraContextoAgendamentoUseCase;
import com.fiap.agendamento.domain.model.EventoComparecimentoMessageDomain;
import com.fiap.agendamento.infrastructure.config.RabbitMQConfig;
import com.fiap.agendamento.entrypoint.consumer.mappers.AgendamentoConsumerMapper;
import com.fiap.agendamentoDomain.gen.model.EventoComparecimentoMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoConsumer {

    private final IntegraContextoAgendamentoUseCase integraContextoAgendamentoUseCase;

    public AgendamentoConsumer(IntegraContextoAgendamentoUseCase integraContextoAgendamentoUseCase) {
        this.integraContextoAgendamentoUseCase = integraContextoAgendamentoUseCase;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ROLLBACK)
    public void consumer(EventoComparecimentoMessageDto eventoComparecimentoMessageDto){
        EventoComparecimentoMessageDomain eventoComparecimentoMessageDomain =
                AgendamentoConsumerMapper.INSTANCE.toEventoComparecimentoMessageDomain(eventoComparecimentoMessageDto);

        integraContextoAgendamentoUseCase.integraContexto(eventoComparecimentoMessageDomain);
    }
}
