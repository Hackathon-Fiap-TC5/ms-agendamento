package com.fiap.agendamento.infrastructure.queue.publisher;

import com.fiap.agendamento.entrypoint.controllers.presenter.AgendamentoPresenter;
import com.fiap.agendamento.infrastructure.config.RabbitMQPublisherConfig;
import com.fiap.agendamento.infrastructure.queue.payload.AgendamentoMessageEvent;
import com.fiap.agendamentoDomain.gen.model.EventoAgendamentoMessagePayloadDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publisher(AgendamentoMessageEvent agendamentoMessageEvent){

        EventoAgendamentoMessagePayloadDto eventoAgendamentoMessagePayloadDto =
                AgendamentoPresenter.toBuildPayloadPublisher(agendamentoMessageEvent);

        rabbitTemplate.convertAndSend(
                RabbitMQPublisherConfig.EXCHANGE_NAME,
                RabbitMQPublisherConfig.ROUTING_KEY,
                eventoAgendamentoMessagePayloadDto
        );
    }
}
