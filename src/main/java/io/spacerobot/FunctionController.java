package io.spacerobot;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {
	
	@RequestMapping("/function")
	public Function function(@RequestParam(value="id") int id) {
		
		// Run command with id 'id'
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("touch /tmp/thisfilewascreatedbyjava");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Function(9001);
	}
}
