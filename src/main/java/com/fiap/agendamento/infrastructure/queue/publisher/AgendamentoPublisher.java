package com.fiap.agendamento.entrypoint.controllers.publisher;

import com.fiap.agendamento.entrypoint.controllers.dto.AgendamentoDTO;
import com.fiap.agendamento.infrastructure.config.RabbitMQPublisherConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoPublisher {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publisher(AgendamentoDTO payload){
        rabbitTemplate.convertAndSend(
                RabbitMQPublisherConfig.EXCHANGE_NAME,
                RabbitMQPublisherConfig.ROUTING_KEY,
                payload
        );

        System.out.print("Mensagem enviado com sucesso!");
    }
}
