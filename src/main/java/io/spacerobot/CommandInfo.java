package io.spacerobot;

public class CommandInfo {
	private int status;
	private String name;
	
	public CommandInfo(int status, String name) {
		this.status = status;
		this.name = name;
	}
	
	public CommandInfo() {
		
	}
	
	public CommandInfo(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getName() {
		return name;
	}
}
