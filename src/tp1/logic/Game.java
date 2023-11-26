package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;
import tp1.util.MyStringUtils;


public class Game implements GameStatus, GameModel, GameWorld {

	//TODO fill with your code

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle;
	
	//TODO fill with your code

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

	//CONTROL METHODS
	
	public boolean isFinished() {
		// TODO fill with your code
		return false;
	}

	public void exit() {
		// TODO fill with your code
	}

	public void update() {
	    this.currentCycle++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}

	// TODO fill with your code

	//CALLBACK METHODS
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}

	// TODO fill with your code
	
	//VIEW METHODS
	
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
		// TODO fill with your code
		return 0;
	}

	@Override
	public int getRemainingAliens() {
		// TODO fill with your code
		return 0;
	}


	@Override
	public boolean shootLaser() {
		return false;
	}

	@Override
	public void reset() {

	}
	@Override
	public boolean move(Move move){
		Position playerPos = player.getPos();
		boolean finish;
		if (isOutOfBoundX(playerPos.move(move)))
			finish = false;
		else{
			this.player.performMovement(move);
			finish = true;
			}
	return finish;
	}
	public static boolean isOutOfBoundX(Position position) {
		return (position.col >= DIM_X || position.col < 0);
	}
}
