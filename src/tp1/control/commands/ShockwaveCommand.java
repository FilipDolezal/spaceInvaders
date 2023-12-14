package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.control.exceptions.GameModelException;
import tp1.control.exceptions.NoShockWaveException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShockwaveCommand extends NoParamsCommand {
    //Get all the attributes of the command.
    protected String getName() {
        return Messages.COMMAND_SHOCKWAVE_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOCKWAVE_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOCKWAVE_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            game.executeShockwave();
            game.update();
            return true;
        } catch (GameModelException e) {
            throw new CommandExecuteException(e.getMessage());
        }
    }


}
