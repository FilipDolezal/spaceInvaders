package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.control.exceptions.InitializationException;
import tp1.control.exceptions.OffWorldException;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

import java.util.NoSuchElementException;

public class AlienManager  {

	private Game game;
	private Level level;
	private Ufo actualUFO;
	private int
			remainingAliens,		// keeps track of remaining alien count
			lastCycle;				// for determination if this cycle is new or old
	private boolean
			onBorder,
			descend,
			aliensWin,
			playerWin;
	private Move
			alienShipMove,
			alienShipDirection;

	public int getRemainingAliens() {
		return remainingAliens;		//Return the number of aliens that are alive in the actual game.
	}

	/**
	 * Subtracts 1 to the number of remaining aliens when the player has killed one. <br>
	 * Also checks if the number of aliens is 0 in order to finish the game.
	 */
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

	/**
	 * Initializes the Container with the desired aliens given by a determined configuration
	 * given by the player as an input.
	 * @param config input from the player.
	 * @return the container with the distribution of aliens.
	 */
	public GameObjectContainer initialize(InitialConfiguration config) throws InitializationException {

		/* reset instance variables on initialize */
		this.remainingAliens = 0;
		this.actualUFO = null;
		this.alienShipMove = Move.NONE; 		// current alien move
		this.alienShipDirection = Move.LEFT; 	// current alien direction
		this.onBorder 	= false;	// determine if there is a ship on border in current cycle
		this.descend 	= false;	// keeps track if aliens descended last cycle
		this.aliensWin 	= false;	// determinate if aliens reached player row
		this.playerWin 	= false;	// determinate if player won by destroying aliens

		GameObjectContainer container = new GameObjectContainer();	//Creates a new container of Objects.

		initializeUFO(container);	//Checks if the Ufo must be generated.
		//If the InitialConfiguration is null, then initialize as a normal game.
		if(config == InitialConfiguration.NONE) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		} else {	//If the configuration is a valid one, initialize the container with that configuration of aliens.
			initializeFromConfig(container, config);
		}

		return container;	//Return the container with the aliens.
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
	 * @return true if aliens should move, false otherwise
	 */
	private boolean doAliensMove() {
		return this.lastCycle % level.numCyclesToMoveOneCell == 0;
	}

	/**
	 * Will calculate the right Movement while any of the AlienShips is on the border
	 */
	private void calculateDescend() {
		if(!doAliensMove()) {		//If aliens shouldn't move, set move to none.
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

	/**
	 * Randomly creates the Ufo and adds it to the container of objects.
	 * @param container
	 */
	public void initializeUFO(GameObjectContainer container) {
		if (actualUFO == null && this.game.tryUfoSpawnChange()) {	//If the actualUfo is null and the random condition is true
            Ufo newUfo = new Ufo(game, new Position(8, 0), 1);
			container.add(newUfo);
			actualUFO = newUfo;
			//Creates a new Ufo, and it is added to the container.
        }
		//If the actualUfo is not null, but it is not in the board, or it is dead, then make it null and remove it.
		else if (actualUFO != null && !actualUFO.isAlive()){
			container.remove(actualUFO);
			actualUFO = null;
		}

	}

	/**
	 * Initializes the Regular Aliens in the board.
	 * @param container
	 */
	private void initializeRegularAliens(GameObjectContainer container) {
		for (int row = 0, index = 0; row < level.numRowsRegularAliens; row++) {
			for (int col = 0; col < level.getNumAliensPerRow(); col++, index++) {
				int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);

				container.add(new RegularAlien(
						this.game,
						new Position(col + reqCenter , row + 1),
						this
				));

				remainingAliens++;
			}
		}
	}

	/**
	 * Initializes the Destroyer Aliens in the board.
	 * @param container
	 */
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

	/**
	 * Initializes the game with the desired configuration.
	 * @param container
	 * @param config
	 */
	private void initializeFromConfig(GameObjectContainer container, InitialConfiguration config) throws InitializationException {
			for(String line: config.getShipDescription()) {	//Gets the type of ship
				String[] w = line.split("\\s+");
				try {
					container.add(ShipFactory.spawnAlienShip(
							w[0],
							this.game,
							Position.create(Integer.valueOf(w[1]),Integer.valueOf(w[2])),
							this
					));
					remainingAliens++;

				} catch (NoSuchElementException e) {
					throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(w[0]));
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new InitializationException(Messages.INCORRECT_ENTRY.formatted(line));
				} catch (OffWorldException e) {
					throw new InitializationException(e.getMessage());
				} catch (NumberFormatException e) {
					throw new InitializationException(Messages.INVALID_POSITION.formatted(w[1], w[2]));
				}
			}
	}
}
