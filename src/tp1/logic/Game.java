package tp1.logic;

import tp1.logic.gameobjects.*;

import java.util.Random;

public class Game {
	public static final int DIM_X = 9, DIM_Y = 8;
	private UCMShip UCMship;
	private UCMLaser laser;
	private Ufo ufo;
	private final AlienManager alienManager;
	private final Level level;
	private final Random random = new Random();
	private final long seed;
	private int cycleCount = 0;
	private int points = 0;
	private boolean shockWave = false;

	public Game(Level level, long seed) {
		this.level 	= level;
		this.seed	= seed;
		this.alienManager = new AlienManager(this, level);
		this.UCMship = new UCMShip();
	}

	public String stateToString() {
		String sb = "Life: " + this.UCMship.getHealth() + System.lineSeparator() +
				"Points: " + this.points + System.lineSeparator() +
				"ShockWave: " + (shockWave ? "ON" : "OFF") + System.lineSeparator();
		return sb;
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

	public boolean tryUfoSpawnChange() { return this.ufo == null && random.nextDouble() < level.ufoFrequency; }
	public boolean playerWin() {
		return alienManager.getRemainingAliens() == 0;
	}
	public boolean aliensWin() {
		return alienManager.getSquadInFinalRow() || !this.UCMship.isAlive();
	}

	public String positionToString(int col, int row) {
		for (Alien alien: alienManager.getAliens()) {
			if (alien.getPosition().equals(col, row)) {
				return alien.getSymbol();
			}

			if (alien instanceof DestroyerAlien da) {
				if (da.isBombActive() && da.getBombPosition().equals(col, row)) {
					return Bomb.SYMBOL;
				}
			}
		}

		if(ufo != null && ufo.getPosition().equals(col, row))
			return ufo.getSymbol();

		if(UCMship.getPosition().equals(col, row))
			return UCMShip.SYMBOL;

		if(laser != null && laser.getPosition().equals(col, row))
			return UCMLaser.SYMBOL;

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

	public void disableUfo() {
		this.ufo = null;
	}

	public void enableUfo() {
		this.ufo = new Ufo(this);
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

		// if UFO is null and random true -> spawn ufo
		if(tryUfoSpawnChange()) enableUfo();

		// calculate ufo action if exists
		if(ufo != null) ufo.computerAction();

		// Bombs attack UCMShip
		alienManager.destroyerAttack(UCMship);

		// move aliens
		alienManager.automaticMove();

		// move laser if exists
		if(laser != null) {
			Alien[] aliens = alienManager.getAliens();
			boolean collision; int index = 0;

			do {
				Alien hitAlien = aliens[index];
				collision = laser.performAttack(hitAlien);

				if(hitAlien.getHealth() == 0) {
					if(hitAlien instanceof RegularAlien)
						points += RegularAlien.SCORE;
					else if(hitAlien instanceof DestroyerAlien)
						points += DestroyerAlien.SCORE;
				}

				if(hitAlien instanceof DestroyerAlien) {
					collision = collision || ((DestroyerAlien) hitAlien).isBombHit(laser);
				}

			} while (!collision && ++index < aliens.length);
			// while there is no collision and there are still untested aliens

			// if laser is about to be on highest row AND there was no collision AND ufo is set
			if(!collision && laser.getPosition().inRow(1) && ufo != null) {
				// performAttack on UFO
				collision = laser.performAttack(ufo);
				// ufo was shot down
				if(collision) {
					shockWave = true;
					points += Ufo.SCORE;
				}
			}

			if(!collision) laser.automaticMove();
		}
	}

	/**
	 * attempt to move the UCMship
	 * @param boolean if ship moved
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
		disableLaser();
		if(ufo != null) ufo.remove();
	}

	public boolean performShockWave()
	{
		if(!shockWave) return false;
		for (Alien a: alienManager.getAliens()) a.receiveAttack();
		shockWave = false;
		return true;
	}
}
