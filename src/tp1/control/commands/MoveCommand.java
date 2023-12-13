package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandParseException;
import tp1.control.exceptions.NotAllowedMoveException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

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
	public ExecutionResult execute(GameModel game) {
		switch(move) {	//Avoid the ship going up and down
			case UP:
			case DOWN:
				return new ExecutionResult(Messages.MOVEMENT_ERROR);
		}


		boolean success = game.move(move); 	//If the ship has successfully moved, return true, false otherwise
		if(success) game.update();	//Updates the game

		return new ExecutionResult(success, true, Messages.MOVEMENT_ERROR); 	//Draws the board if true, returns an error otherwise
	}

	@Override
	public Command parse(String[] commandWords) throws NotAllowedMoveException {
		if(commandWords.length != 2) return null;	//If the command is a string with more than two words, return null

		try{
			Move move = Move.valueOf(commandWords[1].toUpperCase());	//Moves to the desired direction.
			return new MoveCommand(move);
		}
		catch(IllegalArgumentException e){
			throw new NotAllowedMoveException(Messages.MOVEMENT_ERROR);
		}

	}

}
