package tp1.control.commands;

import org.junit.platform.commons.util.StringUtils;
import tp1.control.ExecutionResult;
import tp1.control.exceptions.*;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.logic.gameobjects.UCMShip;
import tp1.view.Messages;

import java.util.Arrays;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	//Changes the actual move to the updated move.
	protected MoveCommand(Move move) {
		this.move = move;
	}

	//Get all the attributes of the command.
	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.move(move);
			game.update();
			return true;
		} catch (GameModelException e) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, e);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Move move = null;
		String param = commandWords[1];

		if(StringUtils.isBlank(param))
			// throw error if a parameter is missing
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);

		try{
			move = Move.valueOf(param.toUpperCase());
		} catch(IllegalArgumentException e){
			// throw error if parameter of move is not valid
			throw new CommandParseException(Messages.DIRECTION_ERROR + param);
		}

		return new MoveCommand(move);
	}

}
