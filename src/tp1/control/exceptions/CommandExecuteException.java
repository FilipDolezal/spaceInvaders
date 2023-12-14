package tp1.control.exceptions;

public class CommandExecuteException extends Exception{
    public CommandExecuteException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public CommandExecuteException(String reason) {
        super(reason);
    }
}
