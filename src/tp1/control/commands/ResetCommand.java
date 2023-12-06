package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.InitialConfiguration;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command {

    InitialConfiguration config;
/*
    If the reset command is called with a configuration, applies that configuration to the game.
 */
    private ResetCommand(InitialConfiguration config) {
        this.config = config;
    }
    /*
    If the reset command is called without any configuration, just resets the game.
     */
    public ResetCommand() {
        this.config = null;
    }
    //Get all the attributes of the command.
    protected String getName() {
        return Messages.COMMAND_RESET_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_RESET_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_RESET_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_RESET_HELP;
    }

    @Override
    public ExecutionResult execute(GameModel game) {
        game.reset(this.config);    //Resets the game with the desired configuration.
        return new ExecutionResult(true);
    }

    @Override
    public Command parse(String[] commandWords) {
        // no param option
        if(commandWords.length == 1) return this;

        return new ResetCommand(InitialConfiguration.valueOfIgnoreCase(commandWords[1]));
    }
}
