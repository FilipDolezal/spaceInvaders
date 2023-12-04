package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.util.MyStringUtils;
import tp1.view.Messages;


public class Game implements GameStatus, GameModel, GameWorld {
	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle, score;

	public Level getLevel() {
		return level;
	}
	private final Level level;

	public Game (Level level, long seed){
		this.level = level;
		this.alienManager = new AlienManager(this);
		initGame();
	}
		
	private void initGame () {
		this.container = alienManager.initialize();
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
	}

	// ################## GameStatus functions

	/**
	 * Check which symbol to display on the specified position
	 * @param col number column
	 * @param row number row
	 * @return symbol of the object at the position
	 */
	public String positionToString(int col, int row) {
		Position position = new Position(col, row);
		for (GameObject o: this.container.getObjects()) {
			if(o.isOnPosition(position)) {
				return o.toString();
			}
		}
		return "";
	}
	@Override
	public String infoToString() {
		return null;
	}
	@Override
	public String stateToString() {
		return
				"Life: " 		+ this.player.getLife() + System.lineSeparator() +
				"ShockWave: "	+ ((this.player.getShockwave() == null) ? "NO" : "YES") + System.lineSeparator() +
				"Score: "		+ this.score + System.lineSeparator();
				//...
	}
	@Override
	public boolean playerWin() {
		// TODO fill with your code
		return false;
	}
	@Override
	public boolean aliensWin() {
		return this.alienManager.aliensWin() || !this.player.isAlive();
	}
	@Override
	public int getCycle() {
		return this.currentCycle;
	}
	@Override
	public int getRemainingAliens() {
		return this.container.getRemainingAliens();
	}

	// ################## GameModel functions
	@Override
	public boolean move(Move move) {
		return this.player.move(move);
	}
	@Override
	public boolean shootLaser() {
		return this.player.shootLaser();
	}
	@Override
	public void reset() {
		this.container = alienManager.initialize();
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
		this.currentCycle = 0;


	}

	public boolean executeShockwave(){
		return this.container.performShockwaveOnAliens(this.player.getShockwave());
	}
	public boolean isFinished() {
		return this.aliensWin() || this.playerWin();
	}
	public void exit() {
		System.exit(0);
	}
	public void update() {
		this.currentCycle++;
		this.alienManager.computerActions(this.container);
		this.container.computerActions();
		this.container.automaticMoves();
	}
	
	// ################## GameWorld functions
	/**
	 * for checking if the position is out of the X-axis bound
	 * @param pos the position to check
	 * @return true if in bounds
	 */
	public boolean inBoundsX(Position pos) {
		return pos.col >= 0 && pos.col < DIM_X;
	}

	/**
	 * for checking if the position is out of the Y-axis bound
	 * @param pos the position to check
	 * @return true if in bounds
	 */
	public boolean inBoundsY(Position pos) { return pos.row >= 0 && pos.row < DIM_Y; }

	/**
	 * For checking and attacking every possible Enemy target on the board with UCMWeapon
	 * @param ucmWeapon the weapon of the attack
	 * @return true if Weapon collided with anything
	 */
	public boolean attackEnemy(UCMWeapon ucmWeapon) {
		return this.container.performAttackOnAliens(ucmWeapon);
	}

	/**
	 * For checking and attacking the player with any of the EnemyWeapons
	 * @param enemyWeapon the weapon of the attack
	 * @return true if weapon hit player ship
	 */
	public boolean attackPlayer(EnemyWeapon enemyWeapon) { return enemyWeapon.performAttack(this.player); }

	/**
	 * for adding object to the object container
	 * @param object
	 */
	public void addObject(GameObject object) {
		this.container.add(object);
	}

	/**
	 * for removing object from the object container
	 * @param object
	 */
	public void removeObject(GameObject object) {
		this.container.remove(object);
	}

	/**
	 * For giving the player the ShockWave power-up
	 */
	public void obtainShockwave() { this.player.obtainShockwave(); }

	/**
	 * For increasing the score
	 * @param byNumber number to increase the score by
	 */
	public void increaseScore(int byNumber) {
		this.score += byNumber;
	}
}
