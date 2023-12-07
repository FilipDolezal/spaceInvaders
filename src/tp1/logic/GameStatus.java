package tp1.logic;

/**
 * Methods used for the GamePrinter.
 */
public interface GameStatus {

	public String positionToString(int x, int y);
	public String infoToString();
	public String stateToString();
	
	public boolean playerWin();
	public boolean aliensWin();

	public int getCycle();
	public int getRemainingAliens();

}
