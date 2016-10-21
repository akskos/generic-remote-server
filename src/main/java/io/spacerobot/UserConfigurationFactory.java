package io.spacerobot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class UserConfigurationFactory {
	
	public static String getConfigPath() {
		
		String configPath = "";
		CommandLine cmd = GenericRemoteServer.cmd;
		
		if (cmd.hasOption("c")) {
			configPath = cmd.getOptionValue("c");
		} else {
			String osname = System.getProperty("os.name");
			if (osname.contains("Linux")) {
				String homepath = System.getProperty("user.home");
				configPath = homepath + "/.config/generic-remote-server/config.json";
			} else if (osname.contains("Mac")) {
				String homepath = System.getProperty("user.home");
				configPath = homepath + "/Library/Application Support/Generic Remote Server/config.json";
			} else if (osname.contains("Windows")) {
				String homepath = System.getProperty("user.home");
				configPath = homepath + "/AppData/Local/Generic Remote Server/config.json";
			} else {
				System.out.println("Couldn't detect os type.");
			}
		}
		
		return configPath;
	}
	
	public static UserConfiguration getUserConfiguration() throws IOException {
		
		UserConfiguration config = new UserConfiguration();
		
		// Deserialize json config to java object
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(UserConfiguration.class, new UserConfigurationDeserializer());
		mapper.registerModule(module);
		
		// Get config path and check that the file exists
		String configPath = getConfigPath();
		File configFile = new File(getConfigPath());
		if (!configFile.exists() || configFile.isDirectory()) {
			System.out.println("Could not find file: " + configPath);
		}
		
		// Read config file
		config = mapper.readValue(configFile, UserConfiguration.class);
		
		return config;
	}
}
