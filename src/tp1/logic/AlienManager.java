package tp1.logic;

import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.Ufo;

import java.util.List;
import java.util.Optional;

public class AlienManager  {
	
	private Game game;
	private Level level;

	private int
			remainingAliens,
			cyclesToMove;
	private boolean
			moveAliens = false,
			descend = false,
			aliensWin = false,
			playerWin = false;

	private Move
			alienShipMove = Move.LEFT,
			alienShipDirection = Move.LEFT;


	public int getRemainingAliens() {
		return remainingAliens;
	}

	public boolean aliensWin() {
		return aliensWin;
	}

	public AlienManager(Game game) {
		this.level = game.getLevel();
		this.game = game;
		this.cyclesToMove = level.numCyclesToMoveOneCell;
	}

	public GameObjectContainer initialize() {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeUFO(container);
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);

		return container;
	}

	/**
	 * Computers if AlienShips have to descend and switch directions.
	 * @param container GameObjectContainer instance
	 */
	public void computerActions(GameObjectContainer container) {
		// check if aliens move this cycle
		if(--cyclesToMove != 0) {
			moveAliens = false;
			return;
		} else {
			cyclesToMove = this.level.numCyclesToMoveOneCell;
			moveAliens = true;
		}

		boolean onBorder = container.getAlienShips().stream()
				.filter(s -> s.getPos().inCol(0) || s.getPos().inCol(Game.DIM_Y))
				.findAny().isPresent();

		// if no ships on border -> return
		if(!onBorder) return;

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

			// check if aliens reached last row
			aliensWin = this.reachedPlayerRow(container);
		}
	}

	private boolean reachedPlayerRow(GameObjectContainer container) {
		Integer lowest = container.getAlienShips()
				.stream()
				.map(s -> s.getPos().move(alienShipMove).row)
				.min(Math::min)
				.get();

		return lowest >= Game.DIM_Y - 1;
	}

	public Move getAlienShipMove() {
		return (moveAliens) ? alienShipMove: Move.NONE;
	}

	private void initializeUFO(GameObjectContainer container) {
		container.add(new Ufo(this.game, new Position (Game.DIM_X - 1, 0), 1 ));
	}
	
	private void initializeRegularAliens(GameObjectContainer container) {
		for (int row = 0, index = 0; row < level.numRowsRegularAliens; row++) {
			for (int col = 0; col < level.getNumAliensPerRow(); col++, index++) {
				int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);

				container.add(new RegularAlien(
						this.game,
						this,
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
					this,
					new Position(i + offset, level.numRowsRegularAliens + 1),
					1
			));

			remainingAliens++;
		}
	}
}
