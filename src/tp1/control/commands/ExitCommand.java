package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ExitCommand extends NoParamsCommand{
		  		
		@Override
		public boolean execute(GameModel game) throws CommandExecuteException {
			game.exit();		//Calls exit() in order to end the game.
			return false;	//Not necessary to print the board because the game has finished.
		}
		//Get all the attributes of the command.
		@Override
		protected String getName() {
			return Messages.COMMAND_EXIT_NAME;
		}

		@Override
		protected String getShortcut() {
			return Messages.COMMAND_EXIT_SHORTCUT;
		}

		@Override
		protected String getDetails() {
			return Messages.COMMAND_EXIT_DETAILS;
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_EXIT_HELP;
		}

	}
