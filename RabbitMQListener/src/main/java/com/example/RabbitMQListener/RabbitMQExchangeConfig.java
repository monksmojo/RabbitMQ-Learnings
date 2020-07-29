package com.example.RabbitMQListener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {

    // creation of exchange in rabbitMQ exchange broker
    @Bean
    Exchange exampleExchange(){
        // this will create an  topic exchange
        return new TopicExchange("ExampleExchange");
    }

    // creation of exchange in rabbitMQ exchange broker
    // using declarative ampq spring api
    @Bean
    Exchange exampleExchange2(){
        return ExchangeBuilder.directExchange("ExampleExchange2")
                .autoDelete()
                .internal()
                .build();
    }

    // more advance topicExchange using ExchangeBuilder
    @Bean
    Exchange exampleTopicExchange(){
        return ExchangeBuilder.topicExchange("exampleTopicExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    // more advance fanOutExchange using ExchangeBuilder
    @Bean
    Exchange exampleFanOutExchange(){
        return ExchangeBuilder.fanoutExchange("exampleFanOutExchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    // HeadersExchange using ExchangeBuilder
    @Bean
    Exchange exampleHeadersExchange(){
        return ExchangeBuilder.headersExchange("exampleHeadersExchange")
                .internal()
                .durable(true)
                .ignoreDeclarationExceptions()
                .build();
    }
}


// find answer for these
// what is direct exchange
// what is fanOut exchange
// what is topic exchange
// autoDelete? internal? durable?
