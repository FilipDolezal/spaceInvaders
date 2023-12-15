package tp1.control.exceptions;

import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class OffWorldException extends GameModelException{
    public OffWorldException(Move move, Position currentPos){
        super(Messages.OFF_WORLD_MESSAGE.formatted(move, currentPos));
    }

    public OffWorldException(Position invalidPosition) {
        super(Messages.OFF_WORLD_POSITION.formatted(invalidPosition));
    }
}
