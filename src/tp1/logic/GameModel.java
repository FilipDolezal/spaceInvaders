package tp1.logic;

import tp1.control.InitialConfiguration;

public interface GameModel {
    public boolean move(Move move);
    public boolean shootLaser();
    public boolean shootSuperLaser();
    public boolean executeShockwave();
    public void reset(InitialConfiguration config);
    public boolean isFinished();
    public void exit();
    public void update();

}
