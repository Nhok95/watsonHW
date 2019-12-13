package com.example.watsonassistant.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;
import com.example.watsonassistant.configurations.WatsonAssistantConfig;
import com.example.watsonassistant.model.WatsonAssistantMessage;

@Service
public class WatsonAssistantService {

	private final WatsonAssistantConfig config;
	private SessionResponse session;
	private Assistant service;
	
	private WatsonAssistantService(WatsonAssistantConfig config) {
		this.config = config;
	}
	
	private SessionResponse createSession(Assistant service) {
		
		CreateSessionOptions createSessionOptions = 
				new CreateSessionOptions.Builder(config.getId()).build();
		return service.createSession(createSessionOptions).execute().getResult();
	}
	
	private Assistant connect() {
		
		try {
			Authenticator authenticator = new IamAuthenticator(config.getApikey());
			Assistant service = new Assistant(config.getVersionDate(), authenticator);
			service.setServiceUrl(config.getUrl());
			//Crear sesión.
			return service;
		} catch (Error e) {
			throw new Error(e);
		}
	}
	
	public WatsonAssistantMessage sendMessage(String message) throws JsonProcessingException {
		
		try {
			if(this.service == null) {
				this.service = connect();
				this.session = createSession(service);
			}
			ObjectMapper mapper = new ObjectMapper();
            System.out.println(session.getSessionId());
            MessageInput input = new MessageInput.Builder()
                    .text(message)
                    .build();
            MessageOptions messageOptions = new MessageOptions.Builder()
                    .assistantId(config.getId())
                    .sessionId(session.getSessionId())
                    .input(input)
                    .build();
            MessageResponse messageResponse = service.message(messageOptions).execute().getResult();
            return mapper.readValue(messageResponse.toString(), WatsonAssistantMessage.class);
		} catch (JsonMappingException e) {
			throw new Error(e);
		}
	}
	
	
}
