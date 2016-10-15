package io.spacerobot;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

public class UserConfigurationDeserializer extends StdDeserializer<UserConfiguration> {
	
	public UserConfigurationDeserializer() {
		this(null);
	}
	
	public UserConfigurationDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public UserConfiguration deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		UserConfiguration conf = new UserConfiguration();
		
		JsonNode node = p.getCodec().readTree(p);
		
		// Commands
		JsonNode commandArray = node.get("commands");
		if (commandArray.isArray()) {
			for (JsonNode c : commandArray) {
				conf.addCommand(c.asText());
			}
		}
		
		// Password
		JsonNode usingPasswordNode = node.get("usingPassword");
		conf.setUsingPassword(usingPasswordNode.asBoolean());
		if (conf.usingPassword()) {
			JsonNode passwordNode = node.get("password");
			conf.setPassword(passwordNode.asText());
		}
		
		return conf;
	}
}
