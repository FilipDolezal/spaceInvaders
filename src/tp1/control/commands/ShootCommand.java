package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.control.exceptions.GameModelException;
import tp1.control.exceptions.LaserInFlightException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootCommand extends NoParamsCommand{

    public ShootCommand() {}

    //Get all the attributes of the command.
    @Override
    protected String getName() {
        return Messages.COMMAND_SHOOT_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOOT_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOOT_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOOT_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            game.shootLaser();
            game.update();
            return true;
        } catch (GameModelException e) {
            throw new CommandExecuteException(Messages.LASER_ERROR, e);
        }
    }
}
