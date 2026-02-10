package com.fiap.agendamento.infrastructure.listeners;

import com.fiap.agendamento.infrastructure.config.RabbitMQConfig;
import com.fiap.agendamentoDomain.gen.model.EventoComparecimentoMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ROLLBACK)
    public void consumer(EventoComparecimentoMessageDto eventoComparecimentoMessageDto){
        //Aplicar logica aqui dentro
    }
}
