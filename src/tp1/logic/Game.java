package tp1.logic;

import tp1.logic.gameobjects.*;

import java.util.Random;

// TODO implementarlo
public class Game {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	public UCMShip UCMship;

	public UCMLaser laser;
	public Bomb bomb;
	public AlienManager alienManager;


	//TODO fill your code
	private Level level;
	private long seed;

	private int cycleCount = 0;
	private int points = 0;

	public Game(Level level, long seed) {
		//TODO fill your code
		this.level 	= level;
		this.seed	= seed;
		this.alienManager = new AlienManager(this, level);
	}


	public int totalPoints(){
		return this.points;
	}
	public String stateToString() {

		return "\n";
	}

	public int getCycle() {
		return this.cycleCount;
	}


	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}

	public String positionToString(int col, int row) {
		for (Alien alien: alienManager.getAliens())
			if(alien.getPosition().equals(col, row))
				return alien.toString();

		if(UCMship.getPosition().equals(col, row))
			return "^__^";

		if(laser != null) {
			if(laser.getPosition().equals(col, row))
				return "oo";

	//	if (bomb != null){
			//if (bomb.getPosition().equals(col, row))
				//return "*";

		//}
		}

		return "";
	}

	public boolean playerWin() {
		return alienManager.getRemainingAliens() == 0;
	}

	public boolean aliensWin() {
		return !this.UCMship.isAlive();
	}

	public void enableLaser() {
		if(laser != null) return;

		laser = new UCMLaser(
			this.UCMship.getPosition(),
			Move.UP, this
		);
	}

	public void disableLaser() {
		this.laser = null;
	}

	public Random getRandom() {
		//TODO fill your code
		return null;
	}

	public Level getLevel() {
		//TODO fill your code
		return null;
	}

	public static boolean isOnBorderX(Position position) {
		return (position.col >= DIM_X-1 || position.col <= 0);
	}

	public static boolean isOutOfBoundX(Position position) {
		return (position.col >= DIM_X || position.col < 0);
	}

	public static boolean isOnBorderY(Position position) {
		return (position.row >= DIM_Y-1 || position.row <= 0);
	}

	public static boolean isOutOfBoundY(Position position) {
		return (position.row >= DIM_Y || position.row < 0);
	}

	/**
	 * method that executes the cycle
	 */
	public void performCycle() {
		// increment cycle
		cycleCount++;

		// move aliens
		alienManager.automaticMove();

		// move laser if exists
		if(laser != null) {
			Alien[] aliens = alienManager.getAliens();
			boolean collision;
			int index = 0;

			do {
				collision = laser.performAttack(aliens[index]);
			} while (!collision && ++index < aliens.length);
			// while there is no collision and there are still untested aliens

			if(!collision) laser.automaticMove();
		}
	}

	/**
	 * attempt to move the UCMship
	 * @param move
	 * @return true if attempt to move was successful
	 */
	public boolean moveShip(Move move)
	{
		if(Game.isOutOfBoundX(UCMship.getPosition().move(move)))
			return false;

		UCMship.preformMovement(move);
		return true;
	}

	public void resetGame()
	{
		cycleCount = 0;
		alienManager.resetAliens();
		UCMship = new UCMShip();
		laser = null;
	}

	public void disableBomb() {
		this.bomb = null;
	}
}
