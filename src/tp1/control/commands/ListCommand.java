package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ListCommand extends NoParamsCommand {

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
    public ExecutionResult execute(GameModel game) {
        System.out.println("[U]CM Ship: damage='1', endurance='3'\n" +
                "[R]egular Alien: points='5', damage='0', endurance='2'\n" +
                "[D]estroyer Alien: points='10', damage='1', endurance='1'\n" +
                "U[f]o: points='25', damage='0', endurance='1'");
        return new ExecutionResult(false);
        }
    }