package tp1.control.commands;

import tp1.control.exceptions.CommandExecuteException;
import tp1.control.exceptions.CommandParseException;
import tp1.logic.GameModel;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	  protected abstract String getName();
	  protected abstract String getShortcut();
	  protected abstract String getDetails();
	  protected abstract String getHelp();

	/**
	 * Execute the command
	 * @param game GameModel of the game instance
	 * @return true if execution was successful
	 * @throws CommandExecuteException if any exceptions while executing occurred
	 */
	public abstract boolean execute(GameModel game) throws CommandExecuteException;

	/**
	 * Parse the command
	 * @param commandWords Player input as string array
	 * @return Command if input was parsed
	 * @throws CommandParseException if input couldn't be parsed
	 */
	public abstract Command parse(String[] commandWords) throws CommandParseException;

	protected boolean matchCommandName(String name) {
		return getShortcut().equalsIgnoreCase(name) ||
			getName().equalsIgnoreCase(name);
	}

}
