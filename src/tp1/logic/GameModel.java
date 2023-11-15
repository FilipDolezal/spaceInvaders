package tp1.logic;

public interface GameModel {
    public boolean move(Move move);
    public boolean shootLaser();
    public void reset();
    public boolean isFinished();
    public void exit();
}
