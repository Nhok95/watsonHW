package com.example.watsonassistant.configurations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "ibm.watson.assistant")
@Validated
public class WatsonAssistantConfig {
	
	@NotNull(message = "La clave de api es obligatoria")
	@NotBlank(message = "La clave de api es obligatoria")
	@Pattern(regexp = "[a-z-A-Z0-9_]*", message = "El id de asistente tiene caracteres no válidos")
	private String apikey;
	
	@NotNull(message = "El id de asistente es obligatoria")
	@NotBlank(message = "El id de asistente es obligatoria")
	@Pattern(regexp = "[a-z-A-Z0-9-]*", message = "El id de asistente tiene caracteres no válidos")
	private String id;
	
	@NotNull(message = "La url del servicio es obligatoria")
	@NotBlank(message = "La url del servicio es obligatoria")
	@Pattern(
		regexp = "https:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/\\/=]*)",
		message = "La url del servicio contiene errores"
	)
	private String url;
	
	@NotNull(message = "La fecha de versión es obligatoria")
	@NotBlank(message = "La fecha de versión obligatoria")
	@Pattern(
		regexp = "^\\d{4}(-)(((0)[0-9])|((1)[0-2]))(-)([0-2][0-9]|(3)[0-1])$", 
		message = "La fecha de versión contiene errores, debe tener el formato DD-MM-AAAA"
	)
	private String versionDate;
	
	//Getters
	public String getApikey() { return apikey;}
	
	public String getId() { return id; }
	
	public String getUrl() { return url; }
	
	public String getVersionDate() { return versionDate; }
	
	//Setters
	public void setApikey(String apikey) { this.apikey = apikey; }
	
	public void setId(String id) { this.id = id; }
	
	public void setUrl(String url) { this.url = url; }
	
	public void setVersionDate(String versionDate) {
		this.versionDate = versionDate;
	}
	
}
