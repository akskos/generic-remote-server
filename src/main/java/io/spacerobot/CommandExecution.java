package io.spacerobot;

public class CommandExecution {
	private final int status;
	
	public CommandExecution(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}
