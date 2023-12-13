package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.control.exceptions.CommandParseException;
import tp1.control.exceptions.NotAllowedMoveException;
import tp1.logic.Game;
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
		 * Execute the command.
		 * 
		 * @param game Where to execute the command.
		 * 
		 * @return {@code ExecutionResult} representing if command was successful and if board must be printed
		 */
	  public abstract ExecutionResult execute(GameModel game) throws CommandExecuteException;
	  
	  public abstract Command parse(String[] commandWords) throws CommandParseException, NotAllowedMoveException;
	  
	  protected boolean matchCommandName(String name) {
		    return getShortcut().equalsIgnoreCase(name) || 
		        getName().equalsIgnoreCase(name);
	  }

}
