package io.spacerobot;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class UserConfigurationFactory {
	public static UserConfiguration getUserConfiguration() throws IOException {
		
		UserConfiguration config = new UserConfiguration();
		
		// Deserialize json config to java object
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(UserConfiguration.class, new UserConfigurationDeserializer());
		mapper.registerModule(module);
		
		// Detect OS for reading config file
		
		String osname = System.getProperty("os.name");
		String configFileName = "";
		if (osname.contains("Linux")) {
			
			String homepath = System.getProperty("user.home");
			configFileName = homepath + "/.config/generic-remote-server/config.json";
			File checkingFile = new File(configFileName);
			if (!checkingFile.exists() || checkingFile.isDirectory()) {
				System.out.println("Could not find file: " + configFileName);
			}
			
		} else if (osname.contains("Mac")) {
			
			String homepath = System.getProperty("user.home");
			configFileName = homepath + "/Library/Application Support/Generic Remote Server/config.json";
			File checkingFile = new File(configFileName);
			if (!checkingFile.exists() || checkingFile.isDirectory()) {
				System.out.println("Could not find file: " + configFileName);
			}
			
		} else if (osname.contains("Windows")) {
			
			String homepath = System.getProperty("user.home");
			configFileName = homepath + "/AppData/Local/Generic Remote Server/config.json";
			File checkingFile = new File(configFileName);
			if (!checkingFile.exists() || checkingFile.isDirectory()) {
				System.out.println("Could not find file: " + configFileName);
			}
			
		} else {
			System.out.println("Couldn't detect os type.");
		}
		
		// Read config file
		config = mapper.readValue(new File(configFileName), UserConfiguration.class);
		
		return config;
	}
}
