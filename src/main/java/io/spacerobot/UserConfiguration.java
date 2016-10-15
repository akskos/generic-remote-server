package io.spacerobot;

import java.util.ArrayList;

public class UserConfiguration {
	private ArrayList<String> commands = new ArrayList<>();
	private boolean usingPassword = false;
	private String password;
	
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
	
	public void setUsingPassword(boolean using) {
		usingPassword = using;
	}
	
	public boolean usingPassword() {
		return this.usingPassword;
	}
	
	public void setPassword(String passwd) {
		password = passwd;
	}
	
	public String getPassword() {
		return password;
	}
}
