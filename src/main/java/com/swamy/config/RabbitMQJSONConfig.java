package com.swamy.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQJSONConfig {

	@Value("${rabbitmq.json.queue.name}")
	private String jsonQueue;

	@Value("${rabbitmq.json.exchange.name}")
	private String jsonExchange;

	@Value("${rabbitmq.json.routingkey.name}")
	private String jsonRoutingkey;

	// Create the Queue
	@Bean
	public Queue queue() {
		return new Queue(jsonQueue);
	}

	// Create the Exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(jsonExchange);
	}

	// Bind Queue to Exchange with Routing Key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(jsonRoutingkey);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

}
