package tp1.control.exceptions;

import tp1.control.InitialConfiguration;

public class InitializationException extends GameModelException {
    public InitializationException(String message, Throwable reason) {
        super(message, reason);
    }

    public InitializationException(String message) {
        super(message);
    }
}
