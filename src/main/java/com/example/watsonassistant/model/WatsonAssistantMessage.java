package com.example.watsonassistant.model;

import com.fasterxml.jackson.databind.JsonNode;

public class WatsonAssistantMessage {
	
	private JsonNode output;
	private JsonNode generic;
	private JsonNode intents;
	private JsonNode entities;
	private String sessionId;
	
	//Getters
	public JsonNode getOutput() { return output; } 
	
	public JsonNode getGeneric() { return generic; }
	
	public JsonNode getIntents() { return this.output.get("intents");}
	
	public JsonNode getEntities() { return this.output.get("entities"); }
	
	public String getSessionId() { return sessionId; }
	
	//Setters
	public void setOutput(JsonNode output) { this.output = output; }
	
	public void setGeneric(JsonNode generic) { this.generic = generic; }
	
	public void setIntents(JsonNode intents) { this.intents = intents; }
	
	public void setEntities(JsonNode entities) { this.entities = entities;}
	
	public void setSessionId(String sessionId) {this.sessionId = sessionId;}

}
