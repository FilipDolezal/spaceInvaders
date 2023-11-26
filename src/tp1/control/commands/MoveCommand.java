package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

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
		switch(move) {
			case UP:
			case DOWN:
				return new ExecutionResult(Messages.MOVEMENT_ERROR);
		}


		boolean success = game.move(move);
		game.update();

		if(success) return new ExecutionResult();
		else return new ExecutionResult(Messages.MOVEMENT_ERROR);
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length != 2) return null;
		if(!matchCommandName(commandWords[0])) return null;

		Move move = Move.valueOf(commandWords[1].toUpperCase());
	    return new MoveCommand(move);
	}

}
