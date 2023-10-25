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
	private boolean onBorder;

	public AlienList regularAliens;
	public AlienList destroyerAliens;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;

		this.regularAliens = this.initializeRegularAliens();
		this.destroyerAliens = this.initializeDestroyerAliens();
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected AlienList initializeRegularAliens() {
		AlienList list = new AlienList(this.level.getNumRegularAliens() * this.level.getNumRowsRegularAliens());
		for (int row = 0, index = 0; row < this.level.getNumRowsRegularAliens(); row++) {
			for (int col = 0; col < this.level.getNumRegularAliens(); col++, index++) {

				list.aliens[index] = new RegularAlien(
						this,
						new Position(col, row)
				);
			}
		}
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	protected AlienList initializeDestroyerAliens() {
		AlienList list = new AlienList(level.getNumDestroyerAliens());
		for(int i = 0; i < list.aliens.length; i++) {
			list.aliens[i] = new DestroyerAlien(
					this,
					new Position(i, level.getNumRowsRegularAliens())
			);
		}

		return list;
	}

	public Alien[] getAliens() {
		Alien[] aliens = new Alien[regularAliens.aliens.length + destroyerAliens.aliens.length];
		System.arraycopy(regularAliens.aliens, 0, aliens, 0, regularAliens.aliens.length);
		System.arraycopy(destroyerAliens.aliens, 0, aliens, regularAliens.aliens.length, destroyerAliens.aliens.length);
		return  aliens;
	}

	public int getRemainingAliens() {
		return this.regularAliens.num + this.destroyerAliens.num;
	}

	public void automaticMove() {
		Alien[] aliens = getAliens();

		boolean isAnyInBorder = false;
		for (Alien a: aliens) {
			if(a == null) continue;
			if(this.game.isOnBorderX(a.position)) {
				isAnyInBorder = true;
				break;
			}
		}

		if(isAnyInBorder && this.onBorder) {
			this.onBorder = false;
			for (Alien a: aliens) {
				if(a == null) continue;
				a.changeDirection();
			}
		} else {
			for (Alien a: aliens) {
				if(a == null) continue;
				a.automaticMove();
			}
			this.onBorder = true;
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
