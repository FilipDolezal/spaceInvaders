package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {
	//Get all the attributes of the command.
	@Override
	protected String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	@Override
	public ExecutionResult execute(GameModel game) {
		System.out.println(Messages.HELP_AVAILABLE_COMMANDS);	//Prints the first line
		System.out.println(CommandGenerator.commandHelp());		//Method created in commandGenerator to print all the help list
		return new ExecutionResult(false);			//No need to redraw the board.
	}
}
