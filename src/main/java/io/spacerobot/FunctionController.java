package io.spacerobot;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class FunctionController {
	
	@RequestMapping("/function")
	public Function function(@RequestParam(value="id") int id) {
		
		// Deserialize json config to java object
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserConfiguration config = mapper.readValue(new File("/home/akseli/.config/generic-remote-server/config.json"), UserConfiguration.class);
			
			System.out.println("----------------------");
			System.out.println("Configured command1: " + config.getCommand1());
			System.out.println("Configured command2: " + config.getCommand2());
			System.out.println("----------------------");
			
			// Run the specified command
			Runtime rt = Runtime.getRuntime();
			if (id == 1) {
				rt.exec(config.getCommand1());
				return new Function(9001);
			} else if (id == 2) {
				rt.exec(config.getCommand2());
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
