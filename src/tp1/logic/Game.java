package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.*;
import java.util.Random;

/**
 * Game Class is the glue of the program. Contains the container and performs all the
 * methods over the objects.
 */
public class Game implements GameStatus, GameModel, GameWorld {
	public static final int DIM_X = 9;
	//Dimensions of the board.
	public static final int DIM_Y = 8;

	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private Random random;
	private int currentCycle, score;

	public Level getLevel() {
		return level;
	}
	private final Level level;
	private final long seed;

	/**
	 * Constructor of the game.
	 */
	public Game (Level level, long seed){
		this.level = level;
		this.alienManager = new AlienManager(this);
		this.seed = seed;
		this.random = new Random(seed);
		initGame();
	}

	/**
	 * Initializes the game creating a new container, a new player (UCMShip)
	 * adds the player to the container, and the score is initialized to 0.
	 */
	private void initGame () {
		this.container = alienManager.initialize(null);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
	}

	/**
	 * Checks if the random should generate an Ufo or not.
	 * @return true if it should randomly create the Ufo, false otherwise.
	 */
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

	/**
	 * Displays the status of the game.
	 * @return the life, the points and if the shockwave is available or not.
	 */
	@Override
	public String stateToString() {
		return
				"Life: " 		+ this.player.getLife() + System.lineSeparator() +
				"Points: "		+ this.score + System.lineSeparator() +
				"ShockWave: "	+ ((this.player.getShockwave() == null) ? "OFF" : "ON") + System.lineSeparator();
				//...
	}

	/**
	 * @return true if the remaining aliens is 0, false otherwise.
	 */
	@Override
	public boolean playerWin() {
		return this.alienManager.playerWin();
	}

	/**
	 * @return true if the aliens have reached the last row or the player is not alive.
	 */
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

	/**
	 * Performs the movement of the ship with the input given by the user.
	 * @param move a valid direction.
	 * @return true if it can be perfomed, false otherwise.
	 */
	@Override
	public boolean move(Move move) {
		return this.player.move(move);
	}

	/**
	 * Shoots the laser if it can be shot.
	 * @return true if it can be shot, false otherwise.
	 */
	@Override
	public boolean shootLaser() {
		return this.player.shootLaser();
	}
	/**
	 * Shoots the SuperLaser if it can be shot.
	 * @return true if it can be shot, false otherwise.
	 */
	@Override
	public boolean shootSuperLaser() {
		return this.player.shootSuperLaser();
	}

	/**
	 * Resets the game applying the desired configuration. If it is null, then the game is
	 * just reset as always. If there's a configuration it is applied when resetting.
	 * @param config configuration given by the player.
	 */
	@Override
	public void reset(InitialConfiguration config) {
		this.random = new Random(seed);
		this.container = alienManager.initialize(config);
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.score = 0;
		this.currentCycle = 0;
	}

	/**
	 * Performs the shockwave.
	 * @return true if it was successfully performed, false otherwise.
	 */
	public boolean executeShockwave(){
		return this.container.performShockwave(this.player.getShockwave());
	}

	/**
	 * Checks if the game is finished.
	 * @return true if aliens or player have won, false otherwise.
	 */
	public boolean isFinished() {
		return this.aliensWin() || this.playerWin();
	}

	/**
	 * Exits the game when called by the ExitCommand.
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Updates the cycle, calls computerActions from the container
	 * calls the automaticMove to move all the objects, clears the container of
	 * deleted objects and checks if the Ufo must be generated in this cycle or not.
	 */
	public void update() {
		this.currentCycle++; //Increase cycle.
		this.container.computerActions();	//Check every object actions.
		this.container.automaticMoves();	//Move all the object that should move.
		this.container.postActions();		//Clear the deleted list container.
		this.alienManager.initializeUFO(this.container);	//Check if the Ufo must be generated.
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
		if(this.score > SuperLaser.COST) {	//If player has more score that the cost of the SuperLaser.
			this.score -= SuperLaser.COST;
			return true;	//Returns true.
		}
		return false; //False otherwise.
	}

	/**
	 * Randomly determinate if DestroyerAlien should drop bomb
	 * @return true if bomb should drop
	 */
	public boolean tryFiringChance(){
		return random.nextDouble() < level.shootFrequency;
	}

}
