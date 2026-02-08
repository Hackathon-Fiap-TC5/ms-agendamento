package com.fiap.agendamento.infrastructure.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPublisherConfig {

    public static final String EXCHANGE_NAME = "agendamento.exchange";
    public static final String ROUTING_KEY = "agendamento.key";
    public static final String QUEUE = "comparecimento.queue";


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageProperties){

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageProperties);
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
