package io.spacerobot;

import java.util.ArrayList;

public class UserConfiguration {
	private ArrayList<String> commands = new ArrayList<>();
	
	public String getCommand(int id) {
		return commands.get(id);
	}
	
	public int addCommand(String command) {
		commands.add(command);
		return commands.size() - 1;
	}
	
	public int numCommands() {
		return commands.size();
	}
}
