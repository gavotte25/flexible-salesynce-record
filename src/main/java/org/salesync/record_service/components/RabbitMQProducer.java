package org.salesync.record_service.components;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQProducer {

    private final Exchange exchange;
    private RabbitTemplate rabbitTemplate;

    //routing key theo các key đã map trong file RabbitMQConfig
    public void sendMessage(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(
                exchange.getName(), routingKey, message);

    }
}