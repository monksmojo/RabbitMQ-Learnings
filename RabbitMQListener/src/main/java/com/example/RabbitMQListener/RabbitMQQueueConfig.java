package com.example.RabbitMQListener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

    // Queue from import org.springframework.amqp.core.Queue;
    @Bean
    Queue exampleQueue(){
        // this will create the not durable queue with name ExampleQueue
        // in RabbitMQ message Broker
        return new Queue("ExampleQueue",false);
    }

    @Bean
    Queue exampleQueue2(){
        // this will create the  durable queue with name ExampleQueue2
        // using declarative api of amqp and spring
        // in RabbitMQ message Broker
        return QueueBuilder.durable("ExampleQueue2")
                .autoDelete()
                .exclusive()
                .build();
    }
}
