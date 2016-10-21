package io.spacerobot;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenericRemoteServer {
	
	public static CommandLine cmd;
	
	public static void main(String[] args) {
		
		// Parse command line
		Options options = new Options();
		options.addOption("c", "config", true, "custom configuration file path");
		CommandLineParser parser = new DefaultParser();
		try {
			cmd = parser.parse(options, args);
			
			if (cmd.hasOption("c")) {
				String customConfigPath = cmd.getOptionValue("c");
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SpringApplication.run(GenericRemoteServer.class, args);
	}
}
