package tp1.control.commands;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) {
		return switch(commandWords[0]) {
			case "help" -> new HelpCommand();
			case "exit" -> new ExitCommand();
			case "shoot" -> new ShootCommand();
			default -> null;
		};
	}
	
}
