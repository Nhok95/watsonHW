package com.example.watsonassistant.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatsonAssistantController {
	
	@RequestMapping(value="/send", method = RequestMethod.GET)
	public String send(@RequestParam("message") String message ) {
		
		return message;
	}

}
