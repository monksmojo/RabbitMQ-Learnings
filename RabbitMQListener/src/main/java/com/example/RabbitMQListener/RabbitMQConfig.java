package com.example.RabbitMQListener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // this configuration contains
    // define the queue to listen to
    private static final String MyQueue="MyQueue";
    // the Queue is from import org.springframework.amqp.core.Queue;
    @Bean
    Queue myQueue(){
        // this will create the new queue from the intellij in rabbitMQ broker
        return new Queue(MyQueue,true);
    }

    // creating the exchange myTopicExchange
    @Bean
    Exchange myTopicExchange(){
        return ExchangeBuilder.topicExchange("myTopicExchange")
                .durable(true)
                .build();
    }

    // binding myTopicExchange to myQueue by creating myBinding
    @Bean
    Binding myBinding(){
//        // way1
//        return new Binding(MyQueue, Binding.DestinationType.QUEUE,
//                "myTopicExchange","topicKey",null);

        //way2
        // using declarative binding builder spring ampq library
        return BindingBuilder
                .bind(myQueue()) // the queue to bind to
                .to(myTopicExchange()) // the exchange from where the message will come
                .with("routingKey")
                .noargs();

    }


    //provide the connection to the queue
    //ConnectionFactory and CachingConnectionFactory is from
    //import org.springframework.amqp.rabbit.connection

    @Bean
    ConnectionFactory connectionFactory(){
        // cache connection so we don't have to create again and again
        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest" );
        return cachingConnectionFactory;
    }

    // Bind the Queue ConnectionFactory and listener  together..
    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener());
        return simpleMessageListenerContainer;
    }

}
