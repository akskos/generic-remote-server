package io.spacerobot;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FunctionController {
	
	@RequestMapping("/function")
	public Function function(@RequestParam(value="id") int id) {
		
		// Deserialize json config to java object
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserConfiguration config = mapper.readValue(new File("/home/akseli/.config/generic-remote-server/config.json"), UserConfiguration.class);
			
			System.out.println("----------------------");
			System.out.println("Configured port number: " + config.getPort());
			System.out.println("Configured command1: " + config.getCommand1());
			System.out.println("Configured command2: " + config.getCommand2());
			System.out.println("----------------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return new Function(9001);
	}
}
