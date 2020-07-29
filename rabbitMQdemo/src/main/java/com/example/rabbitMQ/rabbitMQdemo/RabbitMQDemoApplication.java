package com.example.rabbitMQ.rabbitMQdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQDemoApplication implements CommandLineRunner {
	// commandLineRunner act as startup file script
	// which will always execute on program run
	@Autowired
	private RabbitTemplate rabbitTemplate;
	// rabbitTemplate help us in implementing rabbitMQ

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQDemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("TestExchange1","TestRouting1","hello 1st message from rabbitMQDemo application");
		// the constructor has 3 parameter
		// exchange name provider send message to
		// queue with the routing key which will receive the messages
		// the message we want to send

		// simplest way to exchange message with rabbitMQ broker
		// allow us to send any kind of message with doc string image
		// for now we are sending string object

		// sending object and binary data in the exchange and queue of rabbitmq
		rabbitTemplate.convertAndSend("TestExchange1","TestRouting1",new SimpleMessage("simple message","simple message description"));

		// sending the data from one producer(RabbitMQDemoApplication)
		// to the exchange(myExchange) withBinding(routingKey) and message a simple message object
		// which will be then listened by the consumerApplication("RabbitMqListenerApplication)
		// which will be listening the queue(myQueue)

		SimpleMessage simpleMessage=new SimpleMessage("myMessage","message from the demo application");
		rabbitTemplate.convertAndSend("myTopicExchange","routingKey",simpleMessage);
	}
}
