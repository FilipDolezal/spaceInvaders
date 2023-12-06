package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootSuperLaserCommand extends NoParamsCommand{

    public ShootSuperLaserCommand() {}

    @Override
    protected String getName() {
        return Messages.COMMAND_SHOOT_SUPER_LASER_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOOT_SUPER_LASER_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOOT_SUPER_LASER_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOOT_SUPER_LASER_HELP;
    }

    @Override
    public ExecutionResult execute(GameModel game) {
        boolean success = game.shootSuperLaser();
        if(success) game.update();

        return new ExecutionResult(success, true, Messages.SUPER_LASER_ERROR);
    }
}
