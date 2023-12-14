package tp1.control.exceptions;

import tp1.view.Messages;

public class NoShockWaveException extends GameModelException{
    public NoShockWaveException() {
        super(Messages.SHOCKWAVE_ERROR);
    }
}
