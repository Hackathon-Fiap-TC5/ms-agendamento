package com.fiap.agendamento.infrastructure.producer;

import com.fiap.agendamento.entrypoint.controllers.presenter.AgendamentoPresenter;
import com.fiap.agendamento.infrastructure.config.RabbitMQConfig;
import com.fiap.agendamento.infrastructure.producer.payload.AgendamentoMessageEvent;
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
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                eventoAgendamentoMessagePayloadDto
        );
    }
}
