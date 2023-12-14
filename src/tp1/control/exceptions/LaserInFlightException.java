package tp1.control.exceptions;

import tp1.view.Messages;

public class LaserInFlightException extends GameModelException {
    public LaserInFlightException() {
        super(Messages.LASER_ALREADY_SHOT);
    }
}
