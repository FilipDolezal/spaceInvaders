package tp1.control.commands;

import tp1.control.exceptions.CommandExecuteException;
import tp1.control.exceptions.GameModelException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootSuperLaserCommand extends NoParamsCommand{

    public ShootSuperLaserCommand() {}

    //Get all the attributes of the command.
    @Override
    protected String getName() {
        return Messages.COMMAND_SUPERLASER_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SUPERLASER_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SUPERLASER_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SUPERLASER_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            game.shootSuperLaser();
            game.update();
            return true;
        } catch (GameModelException e)  {
            throw new CommandExecuteException(e.getMessage());
        }
    }
}
