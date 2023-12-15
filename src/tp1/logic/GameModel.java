package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.control.exceptions.*;

/**
 * Methods for the Controller.
 */
public interface GameModel {
    public void move(Move move) throws GameModelException;
    public void shootLaser() throws GameModelException;
    public void shootSuperLaser() throws GameModelException;
    public void executeShockwave() throws GameModelException;
    public void reset(InitialConfiguration config) throws InitializationException;
    public boolean isFinished();
    public void exit();
    public void update();

}
