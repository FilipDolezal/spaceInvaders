package tp1.logic;

//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.Alien;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.AlienList;
//import tp1.logic.lists.DestroyerAlienList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;

	private boolean squadInFinalRow;
	private boolean onBorder = true;
	private int cyclesToMove;

	private AlienList regularAliens;
	private AlienList destroyerAliens;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;

		this.regularAliens = this.initializeRegularAliens();
		this.destroyerAliens = this.initializeDestroyerAliens();
		this.cyclesToMove = (level.numCyclesToMoveOneCell - 1);
	}
		
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	private AlienList initializeRegularAliens() {
		AlienList list = new AlienList(level.numRegularAliens);
		for (int row = 0, index = 0; row < level.numRowsRegularAliens; row++) {
			for (int col = 0; col < level.getNumAliensPerRow(); col++, index++) {

				list.aliens[index] = new RegularAlien(
						this,
						new Position(col + 2 , row + 1)
				);
			}
		}
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	private AlienList initializeDestroyerAliens() {
		AlienList list = new AlienList(level.numDestroyerAliens);
		for(int i = 0; i < list.aliens.length; i++) {
			list.aliens[i] = new DestroyerAlien(
					this,
					new Position(i + 3, level.numRowsRegularAliens + 1)
			);
		}

		return list;
	}

	/**
	 * Returns one array of all the remaining aliens
	 * @return Alien[] remaining aliens
	 */
	public Alien[] getAliens() {
		Alien[] aliens = new Alien[regularAliens.aliens.length + destroyerAliens.aliens.length];
		System.arraycopy(regularAliens.aliens, 0, aliens, 0, regularAliens.aliens.length);
		System.arraycopy(destroyerAliens.aliens, 0, aliens, regularAliens.aliens.length, destroyerAliens.aliens.length);
		return  aliens;
	}

	public int getRemainingAliens() {
		return this.regularAliens.aliens.length + this.destroyerAliens.aliens.length;
	}

	public void automaticMove() {
		Alien[] aliens = getAliens();

		boolean nowOnBorder = false;
		for (Alien a: aliens) {
			if(Game.isOnBorderX(a.getPosition())) {
				nowOnBorder = true;
				break;
			}
		}

		if(nowOnBorder) {
			if(onBorder) {
				// already was on border -> wait for move then reset onBorder
				if(cyclesToMove == 0) onBorder = false;

			} else {
				// newly on border -> descend now
				for (Alien a: aliens) a.changeDirection();
				if(cyclesToMove == 0) cyclesToMove++;
				onBorder = true;
			}
		}

		if(cyclesToMove-- == 0) {
			for (Alien a: aliens) a.automaticMove();
			// reset cycle counter back to default
			cyclesToMove = (level.numCyclesToMoveOneCell-1);
		}
	}

	public void resetAliens() {
		this.destroyerAliens = this.initializeDestroyerAliens();
		this.regularAliens = this.initializeRegularAliens();
		this.onBorder = false;
	}

	public void removeAlien(Alien alien)
	{
		if(alien instanceof DestroyerAlien)
			destroyerAliens.remove(alien);
		else if(alien instanceof  RegularAlien)
			regularAliens.remove(alien);

	}
}
