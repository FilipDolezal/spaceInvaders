package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class NoneCommand extends NoParamsCommand{
    //Get all the attributes of the command.
    @Override
    protected String getName() {
        return Messages.COMMAND_NONE_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_NONE_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_NONE_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_NONE_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        game.update();  //Updates the game after skipping the cycle.
        return true;
    }
}
