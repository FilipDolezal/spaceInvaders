package tp1.logic;

import tp1.logic.gameobjects.*;

import java.util.Random;

public class Game {
	public static final int DIM_X = 9, DIM_Y = 8;
	public UCMShip UCMship;
	public UCMLaser laser;
	public AlienManager alienManager;

	private Level level;
	private Random random = new Random();
	private long seed;
	private int cycleCount = 0;
	private int points = 0;

	public Game(Level level, long seed) {
		this.level 	= level;
		this.seed	= seed;
		this.alienManager = new AlienManager(this, level);
	}

	public String stateToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Life: ").append(this.UCMship.getHealth()).append(System.lineSeparator());
		sb.append("Points: ").append(this.points).append(System.lineSeparator());
		//sb.append("ShockWave: ").append(this.UCMship.getHealth()).append(System.lineSeparator());
		return sb.toString();
	}
	public int getCycle() {
		return this.cycleCount;
	}
	public Random getRandom() { return this.random; }
	public Level getLevel() { return this.level; }

	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}
	public boolean tryFiringChance() { return random.nextDouble() < level.shootFrequency; }
	public boolean playerWin() {
		return alienManager.getRemainingAliens() == 0;
	}
	public boolean aliensWin() {
		return alienManager.getSquadInFinalRow() || !this.UCMship.isAlive();
	}

	public String positionToString(int col, int row) {
		for (Alien alien: alienManager.getAliens()) {
			if(alien.getPosition().equals(col, row)) {
				return alien.getSymbol();
			}

			if(alien instanceof DestroyerAlien) {
				DestroyerAlien da = (DestroyerAlien) alien;
				if(da.isBombActive() && da.getBombPosition().equals(col, row)) {
					return Bomb.SYMBOL;
				}
			}
		}

		if(UCMship.getPosition().equals(col, row))
			return UCMShip.SYMBOL;

		if(laser != null) {
			if(laser.getPosition().equals(col, row))
				return UCMLaser.SYMBOL;
		}
		return "";
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

	public static boolean isOnBorderX(Position position) {
		return (position.col >= DIM_X-1 || position.col <= 0);
	}

	public static boolean isOutOfBoundX(Position position) {
		return (position.col >= DIM_X || position.col < 0);
	}

	public static boolean isInFinalRow(Position position) {
		return position.row == Game.DIM_Y - 1;
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

		// Bombs attack UCMShip
		alienManager.destroyerAttack(UCMship);

		// move aliens
		alienManager.automaticMove();

		// move laser if exists
		if(laser != null) {
			Alien[] aliens = alienManager.getAliens();
			boolean collision;
			int index = 0;

			do {
				Alien hitAlien = aliens[index];
				collision = laser.performAttack(hitAlien);
				if(hitAlien.getHealth() == 0) {
					if(hitAlien instanceof RegularAlien)
						points += RegularAlien.SCORE;
					else if(hitAlien instanceof DestroyerAlien)
						points += DestroyerAlien.SCORE;
					// else if(hitAlien instanceof Ufo)
						// points += Ufo.SCORE;
				}

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
}
