package com.swamy.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.swamy.dto.User;

@Service
public class RabbitMQJSONProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONProducer.class);
	
	@Value("${rabbitmq.json.queue.name}")
	private String jsonQueue;

	@Value("${rabbitmq.json.exchange.name}")
	private String jsonExchange;

	@Value("${rabbitmq.json.routingkey.name}")
	private String jsonRoutingkey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public String sendJsonMessage(User user) {
		
		rabbitTemplate.convertAndSend(jsonExchange, jsonRoutingkey, user);
		LOGGER.info(String.format("Message Sent Successfully : %s", user.toString()));
		return "Message Sent Successfully";
	}
}







