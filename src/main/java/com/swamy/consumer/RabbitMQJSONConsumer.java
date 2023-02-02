package com.swamy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.swamy.dto.User;

@Service
public class RabbitMQJSONConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);
	
	@RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
	public void readMessage(User user) {
		LOGGER.info(String.format("Received JSON Data : %s", user.toString()));
	}
}
