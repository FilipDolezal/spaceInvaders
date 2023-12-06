package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.*;
import tp1.util.MyStringUtils;
import tp1.view.Messages;
import java.util.Random;


public class Game implements GameStatus, GameModel, GameWorld {
	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private final Random random;
	private int currentCycle, score;

	public Level getLevel() {
		return level;
	}
	private final Level level;

	public Game (Level level, long seed){
		this.level = level;
		this.alienManager = new AlienManager(this);
		this.random = new Random(seed);
		initGame();
	}

	private void initGame () {
		this.container = alienManager.initialize(null);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
	}

	public boolean tryUfoSpawnChange() {
		return random.nextDouble() < level.ufoFrequency;
	}

	// ################## GameStatus functions
	/**
	 * Check which symbol to display on the specified position
	 * @param col number column
	 * @param row number row
	 * @return symbol of the object at the position
	 */
	public String positionToString(int col, int row) {
		return this.container.positionToString(col, row);
	}
	@Override
	public String infoToString() {
		return null;
	}
	@Override
	public String stateToString() {
		return
				"Life: " 		+ this.player.getLife() + System.lineSeparator() +
				"Points: "		+ this.score + System.lineSeparator() +
				"ShockWave: "	+ ((this.player.getShockwave() == null) ? "OFF" : "ON") + System.lineSeparator();
				//...
	}
	@Override
	public boolean playerWin() {
		return this.alienManager.playerWin();
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
		return this.alienManager.getRemainingAliens();
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
	public boolean shootSuperLaser() {
		return this.player.shootSuperLaser();
	}
	@Override
	public void reset(InitialConfiguration config) {
		this.container = alienManager.initialize(config);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
		this.currentCycle = 0;
	}

	public boolean executeShockwave(){
		return this.container.performShockwave(this.player.getShockwave());
	}
	public boolean isFinished() {
		return this.aliensWin() || this.playerWin();
	}
	public void exit() {
		System.exit(0);
	}
	public void update() {
		this.currentCycle++;
		this.container.computerActions();
		this.container.automaticMoves();
		this.container.postActions();
		this.alienManager.initializeUFO(this.container);
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
	 * @param weapon the weapon of the attack
	 * @return true if Weapon collided with anything
	 */
	public boolean performAttack(UCMWeapon weapon) {
		return this.container.performAttack(weapon);
	}

	/**
	 * For checking and attacking the player with any of the EnemyWeapons
	 * @param weapon the weapon of the attack
	 * @return true if weapon hit player ship
	 */
	public boolean performAttack(EnemyWeapon weapon) {
		return weapon.performAttack(this.player);
	}

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

	/**
	 * For decreasing alienCounter in alienManager
	 */
	public void decreaseAlienCount() { this.alienManager.decreaseAlienCount(); }

	/**
	 * Determine if SuperLaser may be shot
	 * If yes -> decrease the score by SuperLaser cost
	 * @return true if SuperLaser can be shot
	 */
	public boolean canShootSuperLaser() {
		if(this.score > SuperLaser.COST) {
			this.score -= SuperLaser.COST;
			return true;
		}
		return false;
	}

	/**
	 * Randomly determinate if DestroyerAlien should drop bomb
	 * @return true if bomb should drop
	 */
	public boolean tryFiringChance(){
		return random.nextDouble() < level.shootFrequency;
	}

}
