package com.brocast.demo.unitarias;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PublisherConfigTest {

    @Value("${rabbitmq.queue.name:auditoriaDepositos}")
    private String auditoriaDepositos;

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Jackson2JsonMessageConverter converter;

    @TestConfiguration
    static class TestRabbitConfig {
        @Bean
        @Primary
        public CachingConnectionFactory connectionFactory() {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);
            return connectionFactory;
        }
    }

    @Test
    void testDepositQueue() {
        // Verificar que la cola tenga el nombre correcto
        assertThat(queue.getName()).isEqualTo(auditoriaDepositos);
        assertThat(queue.isDurable()).isTrue();
    }

    @Test
    void testRabbitTemplate() {
        // Verificar que el RabbitTemplate utiliza el Jackson2JsonMessageConverter
        assertThat(rabbitTemplate.getMessageConverter()).isEqualTo(converter);
    }

    @Test
    void testJackson2JsonMessageConverter() {
        // Verificar que el Jackson2JsonMessageConverter no es nulo
        assertThat(converter).isNotNull();
    }
}
