package com.napkinstudio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.napkinstudio.manager.FTPManager;

@RestController
@RequestMapping(value = "/sapsynchronizerservice")
public class SAPSynchrinizerController {
	
	@Autowired
	FTPManager ftpManager;
	
	@RequestMapping(method = RequestMethod.HEAD)
    public String trigger(@RequestParam(value = "user", defaultValue = "") String user) {
		System.out.println("user = " + user);
		System.out.println("herehereherehereherehere");
        return "BRBRBRBRBRBBRBR " + user + "]";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String postTrigger(@RequestParam(value = "token", defaultValue = "") String token) {
		System.out.println();
		if(token.equals("LJDFGHUYOI*$(**(GSLSJGHJSFHIOIGUHIYHGEOIJFHDSGUIJERHOIUFSGHSIOUDGRHGIUSHFGUDSFH")) {
			System.out.println("next actions!");
			return ftpManager.handle();
		} else {
			System.out.println("nope!");
			return "trigger failed";
		}
    }
	
}
