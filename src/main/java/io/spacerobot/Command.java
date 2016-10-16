package io.spacerobot;

public class Command {
	private String name;
	private String cmd;
	
	public Command(String name, String cmd) {
		this.name = name;
		this.cmd = cmd;
	}
	
	public Command() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getCmd() {
		return cmd;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
