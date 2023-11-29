package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.UCMWeapon;
import tp1.util.MyStringUtils;


public class Game implements GameStatus, GameModel, GameWorld {
	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle;

	public Level getLevel() {
		return level;
	}
	private Level level;

	public Game (Level level, long seed){
		this.level = level;
		this.alienManager = new AlienManager(this);
		initGame();
	}
		
	private void initGame () {
		this.container = alienManager.initialize();
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
	}

	// ################## GameStatus functions
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
		// TODO fill with your code
		return null;
	}
	@Override
	public String stateToString() {
		// TODO fill with your code
		return null;
	}
	@Override
	public boolean playerWin() {
		// TODO fill with your code
		return false;
	}
	@Override
	public boolean aliensWin() {
		// TODO fill with your code
		return false;
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
	public void reset() {

	}
	public boolean isFinished() {
		// TODO fill with your code
		return false;
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
	public boolean inBoundsX(Position pos) {
		return pos.col >= 0 && pos.col < DIM_X;
	}

	public boolean inBoundsY(Position pos) { return pos.row >= 0 && pos.row < DIM_Y; }

	public boolean attackEnemy(UCMWeapon ucmWeapon) {
		return this.container.performAttackOnAliens(ucmWeapon);
	}

	public void addObject(GameObject object) {
		this.container.add(object);
	}

	public void removeObject(GameObject object) {
		this.container.remove(object);
	}
}
