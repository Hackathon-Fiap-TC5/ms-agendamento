package com.fiap.agendamento.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "agendamento.exchange";
    public static final String ROUTING_KEY = "agendamento.key";
    public static final String QUEUE = "comparecimento.queue";

    public static final String QUEUE_ROLLBACK = "agendamento.queue";
    public static final String ROUTING_KEY_ROLLBACK = "agendamento.rollback";

    // 1. Declaração das Filas
    @Bean
    public Queue comparecimentoQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public Queue retornoQueue() {
        return QueueBuilder.durable(QUEUE_ROLLBACK).build();
    }

    // 2. Declaração da Exchange (Topic é mais flexível)
    @Bean
    public TopicExchange agendamentoExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 3. Bindings (Ligações)
    @Bean
    public Binding bindingIda() {
        return BindingBuilder.bind(comparecimentoQueue())
                .to(agendamentoExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingVolta() {
        return BindingBuilder.bind(retornoQueue())
                .to(agendamentoExchange())
                .with(ROUTING_KEY_ROLLBACK);
    }

    // 4. Infraestrutura de Mensageria
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}