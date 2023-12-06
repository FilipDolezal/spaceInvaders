package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootCommand extends NoParamsCommand{

    public ShootCommand() {}

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
    public ExecutionResult execute(GameModel game) {
        boolean success = game.shootLaser();
        if(success) game.update();

        return new ExecutionResult(success, true, Messages.LASER_ERROR);
    }
}
