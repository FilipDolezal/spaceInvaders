package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new ExitCommand(),
		new ShootCommand(),
		new MoveCommand()
		//TODO fill with your code
	);

	public static Command parse(String[] commandWords) {
		if(commandWords.length == 0) return null;
		Command command = null;
		for (Command c: availableCommands) {
			command = c.parse(commandWords);
			if(command != null) break;
		}
		return command;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands
					.append("\t").append(c.getName()).append(":\t")
					.append(c.getHelp()).append(System.lineSeparator())
					.append("\t\t\t").append(c.getDetails()).append(System.lineSeparator());
		}
		return commands.toString();
	}

}
