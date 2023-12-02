package tp1.logic;

public interface GameModel {
    public boolean move(Move move);
    public boolean shootLaser();
    public boolean executeShockwave();
    public void reset();
    public boolean isFinished();
    public void exit();
    public void update();

}
