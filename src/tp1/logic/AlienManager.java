package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.Ufo;

public class AlienManager  {
	
	private Game game;
	private int remainingAliens;
	
	public AlienManager(Game game) {
		this.game = game;
	}

	public GameObjectContainer initialize() {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeUFO(container);
		initializeRegularAliens(container);
		initializeDestroyerAliens(container);
		
		//TODO fill with your code
		
		
		return container;
	}
	
	private void initializeUFO(GameObjectContainer container) {
		// container.add(new Ufo(game));
	}
	
	private void initializeRegularAliens(GameObjectContainer container) {
		Level level = this.game.getLevel();

		for (int row = 0, index = 0; row < level.numRowsRegularAliens; row++) {
			for (int col = 0; col < level.getNumAliensPerRow(); col++, index++) {
				int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);

				container.add(new RegularAlien(
						this.game,
						new Position(col + reqCenter , row + 1),
						2
				));
			}
		}
	}
	
	private void initializeDestroyerAliens(GameObjectContainer container) {
		Level level = this.game.getLevel();

		for(int i = 0; i < level.numDestroyerAliens; i++) {

			int reqCenter = (Game.DIM_X/2) - (level.getNumAliensPerRow()/2);
			int offset = reqCenter + (level.getNumAliensPerRow() / level.numDestroyerAliens) - 1;

			container.add(new DestroyerAlien(
					this.game,
					new Position(i + offset, level.numRowsRegularAliens + 1),
					1
			));

		}
	}
}
