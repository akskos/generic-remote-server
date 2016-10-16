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
	public Function function(@RequestParam(value="id", required=true) int id,
							@RequestParam(value="password", required=false) String password) {
		
		try {
			
			// Fetch config
			UserConfiguration config = UserConfigurationFactory.getUserConfiguration();
			
			// Run the specified command
			Runtime rt = Runtime.getRuntime();
			if (id > 0 && id <= config.numCommands()) {
				
				// Check password and execute
				if (!config.usingPassword() || config.getPassword().equals(password)) {
					rt.exec(config.getCommand(id-1));
					return new Function(9001);
				} else {
					return new Function(666);
				}
				
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
