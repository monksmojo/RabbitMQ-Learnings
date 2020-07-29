package com.example.RabbitMQListener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQListener implements MessageListener {
    // interface messageListener provided by amqp

    @Override
    public void onMessage(Message message) {
        System.out.println("message=["+new String(message.getBody())+"]");
    }
}
