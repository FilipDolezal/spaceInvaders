package tp1.control.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new NoneCommand(),
		new ShootCommand(),
		new ShockwaveCommand(),
		new ListCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new ShootSuperLaserCommand()
	);

	public static Command parse(String[] commandWords) {
		Optional match = availableCommands.stream()
				.filter(c -> c.matchCommandName(commandWords[0]))
				.findFirst();
		if (Objects.equals(commandWords[0], ""))
			return new NoneCommand();

		return match.isEmpty() ? null : ((Command) match.get()).parse(commandWords);
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands.append(c.getDetails())
					.append(" : ")
					.append(c.getHelp())
					.append(System.lineSeparator());
		}
		return commands.toString();
	}

}