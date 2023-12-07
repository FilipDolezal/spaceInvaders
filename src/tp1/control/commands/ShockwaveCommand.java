package tp1.control.commands;

import tp1.control.ExecutionResult;
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
    public ExecutionResult execute(GameModel game) {
        boolean result = game.executeShockwave();   //Checks if the shockwave was successfully performed.
        if(result) game.update();  //Update the game.
        return new ExecutionResult(result, result, Messages.SHOCKWAVE_ERROR);   //If true print the board again, else display an error.
    }


}
