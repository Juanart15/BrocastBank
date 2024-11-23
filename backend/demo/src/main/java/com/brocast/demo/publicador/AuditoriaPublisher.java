package com.brocast.demo.publicador;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AuditoriaPublisher {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public void send(Object evento) {
        rabbitTemplate.convertAndSend("auditoriaDepositos", evento);
    }
}

