package tp1.control.exceptions;

import tp1.view.Messages;

public class NotEnoughPointsException extends GameModelException{
    public NotEnoughPointsException(int actual, int expected)
    {
        super(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(actual, expected));
    }
}
