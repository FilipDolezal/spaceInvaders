package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ListCommand extends NoParamsCommand {
    //Get all the attributes of the command.
    @Override
    protected String getName() {
        return Messages.COMMAND_LIST_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_LIST_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_LIST_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_LIST_HELP;
    }

    @Override
    /*
      Prints the List of Ships with their respective attributes.
     */
    public boolean execute(GameModel game) throws CommandExecuteException {
        System.out.println("[U]CM Ship: damage='1', endurance='3'\n" +
                "[R]egular Alien: points='5', damage='0', endurance='2'\n" +
                "[D]estroyer Alien: points='10', damage='1', endurance='1'\n" +
                "U[f]o: points='25', damage='0', endurance='1'");
        return false;
    }
}