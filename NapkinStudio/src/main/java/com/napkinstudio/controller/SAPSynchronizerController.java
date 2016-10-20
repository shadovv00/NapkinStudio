package com.napkinstudio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.napkinstudio.manager.FTPManager;

@RestController
@RequestMapping(value = "/sapsynchronizerservice")
public class SAPSynchronizerController {
	
	private final SimpMessagingTemplate messagingTemplate;
	
	public SAPSynchronizerController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	@Autowired
	FTPManager ftpManager;
	
//	@SendTo("/topic/napkin-notifications")
	@RequestMapping(method = RequestMethod.GET)
    public String trigger(@RequestParam(value = "user", defaultValue = "") String user) {
		System.out.println("user = " + user);
		System.out.println("herehereherehereherehere");
		
		
		
		this.messagingTemplate.convertAndSend("/topic/napkin-notifications", "имхо");
		
		
		
		
        return "BRBRBRBRBRBBRBR " + user + "]";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String postTrigger(@RequestParam(value = "token", defaultValue = "") String token) {
		System.out.println();
		System.out.println("token = " + token);
		if(token.equals("LJDFGHUYOI*$(**(GSLSJGHJSFHIOIGUHIYHGEOIJFHDSGUIJERHOIUFSGHSIOUDGRHGIUSHFGUDSFH")) {
			System.out.println("next actions!");
			return ftpManager.handle();
		} else {
			System.out.println("nope!");
			return "trigger failed";
		}
    }
	
}
