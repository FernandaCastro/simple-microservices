package com.fcastro.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {

    private AmqpTemplate amqpTemplate;

    public void publish(String exchange, String routingKey, Object payload){
        log.info("Publishing to {} using RoutingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using RoutingKey {}. Payload: {}", exchange, routingKey, payload);
    }
}
