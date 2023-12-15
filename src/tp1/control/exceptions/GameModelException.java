package tp1.control.exceptions;

public abstract class GameModelException extends Exception {
    public GameModelException(String message) {
        super(message);
    }
    public GameModelException(String message, Throwable reason){
        super(message,reason);
    }
}