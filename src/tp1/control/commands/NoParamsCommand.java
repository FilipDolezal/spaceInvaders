package tp1.control.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class NoParamsCommand extends Command {
    @Override
    public Command parse(String[] commandWords) {
        return this;
    }
}
