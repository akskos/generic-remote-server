package io.spacerobot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {
	
	@RequestMapping("/function")
	public Function function(@RequestParam(value="id") int id) {
		return new Function(9001);
	}
}
