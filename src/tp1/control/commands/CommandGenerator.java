package tp1.control.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),  //Creates a new command of type Help
		new MoveCommand(),	//Creates a new command of type Move
		new NoneCommand(),	//Creates a new command of type None
		new ShootCommand(),	//Creates a new command of type Shoot
		new ShockwaveCommand(),	//Creates a new command of type Shockwave
		new ListCommand(),		//Creates a new command of type List
		new ExitCommand(),   //Creates a new command of type Exit
		new ResetCommand(),		//Creates a new command of type Reset
		new ShootSuperLaserCommand()	//Creates a new command of type SuperLaser

	);

	public static Command parse(String[] commandWords) {
		Optional match = availableCommands.stream()
				.filter(c -> c.matchCommandName(commandWords[0]))  //Checks if the first word is a valid command
				.findFirst();
		if (Objects.equals(commandWords[0], ""))	//Checks if the user has inserted an empty string as command
			return new NoneCommand();	//Returning a NoneCommand()

		return match.isEmpty() ? null : ((Command) match.get()).parse(commandWords);
		//If the command is inside the availableCommands list, returns that command, null otherwise.
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		//When HelpCommand is called, this method prints all the help list.
		for (Command c: availableCommands) {
			commands.append(c.getDetails())
					.append(" : ")
					.append(c.getHelp())
					.append(System.lineSeparator());
		}
		return commands.toString();
	}

}