package tp1.control.exceptions;

import tp1.view.Messages;

public class NotAllowedMoveException extends GameModelException{
    public NotAllowedMoveException() {
        super(Messages.ALLOWED_MOVES_MESSAGE);
    }
}
