package com.swamy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.User;
import com.swamy.producer.RabbitMQJSONProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageRestController {

	@Autowired
	private RabbitMQJSONProducer rabbitMQJSONProducer;

	@PostMapping("/send")
	public ResponseEntity<String> publishMessage(@RequestBody User user) {
		String sentJSONData = rabbitMQJSONProducer.sendJsonMessage(user);
		return ResponseEntity.ok(sentJSONData);
	}
}