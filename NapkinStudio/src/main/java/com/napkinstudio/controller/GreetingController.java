package com.napkinstudio.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import hello.Greeting;
import hello.HelloMessage;

@Controller
public class GreetingController {

	@MessageMapping("/question-to-napkin")
	@SendTo("/topic/napkin-notifications")
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println("Hrelsf " + message);
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + message.getName() + "!");
	}

}