package tp1.control.commands;

import tp1.control.ExecutionResult;
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
    public ExecutionResult execute(GameModel game) {
        boolean success = game.shootSuperLaser();
        if(success) game.update();
            //if the super laser was successful, the game is updated and printed.

        return new ExecutionResult(success, true, Messages.SUPERLASER_ERROR);
    }
}
