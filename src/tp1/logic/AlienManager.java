package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.*;

public class AlienManager  {

	private Game game;
	private Level level;
	private int
			remainingAliens,		// keeps track of remaining alien count
			lastCycle;				// for determination if this cycle is new or old
	private boolean
			onBorder 	= false,	// determine if there is a ship on border in current cycle
			descend 	= false,	// keeps track if aliens descended last cycle
			aliensWin 	= false,	// determinate if aliens reached player row
			playerWin 	= false;	// determinate if player won by destroying aliens
	private Move
			alienShipMove 		= Move.NONE,	// current alien move
			alienShipDirection 	= Move.LEFT;	// current alien direction

	public int getRemainingAliens() {
		return remainingAliens;
	}
	public void decreaseAlienCount() {
		if(--this.remainingAliens == 0)
			playerWin = true;
	}
	public boolean aliensWin() { return aliensWin; }
	public boolean playerWin() { return playerWin; }
	public void setAliensWin() { this.aliensWin = true; }

	public AlienManager(Game game) {
		this.level = game.getLevel();
		this.game = game;
	}

	public GameObjectContainer initialize(InitialConfiguration config) {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeUFO(container);

		if(config == null) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		} else {
			initializeFromConfig(container, config);
		}

		return container;
	}

	/**
	 * Method to determine the next alien move
	 * will check if position is on border and execute all calculations
	 * @param pos position of the ship
	 */
	public void isOnBorder(Position pos) {
		// if this is the first border check in this cycle
		if(this.lastCycle != this.game.getCycle()) {
			this.lastCycle 	= this.game.getCycle();
			// clear onBorder to start fresh
			this.onBorder = false;
		} else
			// if there is already ship on border -> we don't need to check other ships
			if(this.onBorder) return;

		// if ship is on border
		if(pos.inCol(0) || pos.inCol(Game.DIM_Y)) {
			// set onBorder to true
			this.onBorder = true;
			// determine what move should be next
			this.calculateDescend();
		} else {
			// ship not on border -> if aliens move keep the current direction
			alienShipMove = doAliensMove() ? alienShipDirection : Move.NONE;
		}
	}

	/**
	 * Determine if AlienShips move this cycle
	 * @return true if aliens should move
	 */
	private boolean doAliensMove() {
		return this.lastCycle % level.numCyclesToMoveOneCell == 0;
	}

	/**
	 * Will calculate the right Movement while any of the AlienShips is on the border
	 */
	private void calculateDescend() {
		if(!doAliensMove()) {
			alienShipMove = Move.NONE;
			return;
		};

		if(descend) {
			// if AlienShips descended last round -> switch directions and set move = direction
			descend = false;
			alienShipDirection = switch (alienShipDirection) {
				case LEFT -> Move.RIGHT;
				case RIGHT -> Move.LEFT;
				default -> throw new RuntimeException("Alien Ships bad direction");
			};
			alienShipMove = alienShipDirection;
		} else {
			// if AlienShips did not descend last round -> set move to DOWN
			alienShipMove = Move.DOWN;
			descend = true;
		}

	}

	/**
	 * Get calculated ship movement
	 * @return Movement of all the alienShips
	 */
	public Move getAlienShipMove() {return this.alienShipMove;}

	public void initializeUFO(GameObjectContainer container) {
		if (actualUFO == null && this.game.tryUfoSpawnChange()) {
            Ufo newUfo = new Ufo(game, new Position(Game.DIM_X - 1, 0), 1);
			container.add(newUfo);
			actualUFO = newUfo;
        }
		else if (actualUFO != null && !actualUFO.isAlive()){
			container.remove(actualUFO);
			actualUFO = null;
		}

	}

	private void initializeRegularAliens(GameObjectContainer container) {
		for (int row = 0, index = 0; row < level.numRowsRegularAliens; row++) {
			for (int col = 0; col < level.getNumAliensPerRow(); col++, index++) {
				int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);

				container.add(new RegularAlien(
						this.game,
						new Position(col + reqCenter , row + 1),
						2
				));

				remainingAliens++;
			}
		}
	}
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		for(int i = 0; i < level.numDestroyerAliens; i++) {

			int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);
			int offset = reqCenter + (level.getNumAliensPerRow() / level.numDestroyerAliens) - 1;

			container.add(new DestroyerAlien(
					this.game,
					new Position(i + offset, level.numRowsRegularAliens + 1),
					this
			));

			remainingAliens++;
		}
	}

	private void initializeFromConfig(GameObjectContainer container, InitialConfiguration config) {
		for(String line: config.getShipDescription()) {
			String[] w = line.split("\\s+");
			Position pos = new Position(
				Integer.valueOf(w[1]),
				Integer.valueOf(w[2])
			);
			container.add(ShipFactory.spawnAlienShip(w[0],this.game,pos,this));
		}
	}
}
