package tp1.logic;

//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
//import tp1.logic.lists.DestroyerAlienList;

import java.util.ArrayList;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;
	private int remainingAliens;
	
	private boolean squadInFinalRow;
	private boolean onBorder;

	public ArrayList<RegularAlien> regularAliens;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;

		this.regularAliens = this.initializeRegularAliens();
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected ArrayList<RegularAlien> initializeRegularAliens() {
		ArrayList<RegularAlien> list = new ArrayList<RegularAlien>();
		for (int row = 0; row < this.level.getNumRowsRegularAliens(); row++) {
			for (int col = 0; col < this.level.getNumRegularAliens(); col++) {
				list.add(new RegularAlien(
						this,
						new Position(col, row)
				));
			}
		}
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
//	protected  DestroyerAlienList initializeDestroyerAliens() {
//		//TODO fill your code
//	}

	
	public void automaticMove() {
		boolean isAnyInBorder = regularAliens.stream()
				.filter(a -> this.game.isOnBorderX(a.position))
				.findAny().isPresent();

		if(isAnyInBorder && this.onBorder) {
			this.onBorder = false;
			regularAliens.forEach(a -> a.changeDirection());
		} else {
			regularAliens.forEach(a -> a.automaticMove());
			this.onBorder = true;
		}
	}
}
