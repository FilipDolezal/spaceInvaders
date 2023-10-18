package tp1.logic;

import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;

import java.util.Random;

// TODO implementarlo
public class Game {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	public UCMShip UCMship;
	public AlienManager alienManager;


	//TODO fill your code
	private Level level;
	private long seed;

	public Game(Level level, long seed) {
		//TODO fill your code
		this.level 	= level;
		this.seed	= seed;
		this.alienManager = new AlienManager(this, level);
	}

	public String stateToString() {
		//TODO fill your code
		return null;
	}

	public int getCycle() {
		//TODO fill your code
		return 0;
	}

	public int getRemainingAliens() {
		//TODO fill your code
		return 0;
	}

	public String positionToString(int col, int row) {
		for (RegularAlien alien: alienManager.regularAliens) {
			if(alien.position.equals(col, row))
				return "R["+ alien.getHealth() +"]";
		}

		if(UCMship.position.equals(col, row))
			return "^__^";

		UCMLaser laser = UCMship.getLaser();
		if(laser != null) {
			if(laser.position.equals(col, row))
				return "oo";
		}

		return "";
	}

	public boolean playerWin() {
		//TODO fill your code
		return false;
	}

	public boolean aliensWin() {
		//TODO fill your code
		return false;
	}

	public void enableLaser() {
		//TODO fill your code		
	}

	public void disableLaser() {
		this.UCMship.disableLaser();
	}

	public Random getRandom() {
		//TODO fill your code
		return null;
	}

	public Level getLevel() {
		//TODO fill your code
		return null;
	}

	public boolean isOnBorderX(Position position) {
		return (position.col >= DIM_X-1 || position.col <= 0);
	}

	public boolean isOutOfBoundX(Position position) {
		return (position.col >= DIM_X || position.col < 0);
	}

	public boolean isOnBorderY(Position position) {
		return (position.row >= DIM_Y-1 || position.row <= 0);
	}

	public boolean isOutOfBoundY(Position position) {
		return (position.row >= DIM_Y || position.row < 0);
	}
}
