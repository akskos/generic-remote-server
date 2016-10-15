package io.spacerobot;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@CrossOrigin
@RestController
public class FunctionController {
	
	@RequestMapping(value="/function", produces="application/json", consumes="text/plain")
	public Function function(@RequestParam(value="id") int id) {
		
		// Deserialize json config to java object
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(UserConfiguration.class, new UserConfigurationDeserializer());
		mapper.registerModule(module);
		try {
			
			UserConfiguration config = new UserConfiguration();
			
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
			
			// Run the specified command
			Runtime rt = Runtime.getRuntime();
			if (id > 0 && id <= config.numCommands()) {
				rt.exec(config.getCommand(id-1));
				return new Function(9001);
			} else {
				return new Function(666);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new Function(666);
		}
	}
}
