package io.spacerobot;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CommandInfoController {
	
	@RequestMapping(value="/commandInfo", produces="application/json", consumes="text/plain")
	public CommandInfo commandInfo(@RequestParam(value="id", required=true) int id,
			@RequestParam(value="password", required=false) String password) {
		
		try {
			
			// Fetch config
			UserConfiguration config = UserConfigurationFactory.getUserConfiguration();
			
			// Run the specified command
			if (id > 0 && id <= config.numCommands()) {
				
				// Check password and execute
				if (!config.usingPassword() || config.getPassword().equals(password)) {
					
					String commandName = config.getCommandName(id);
					
					return new CommandInfo(9001, commandName);
				} else {
					return new CommandInfo(666);
				}
				
			} else {
				return new CommandInfo(666);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new CommandInfo(666);
		}
	}
}
