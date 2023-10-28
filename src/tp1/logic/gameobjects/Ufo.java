package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;


public class Ufo extends Alien{

	//TODO fill your code

	private boolean enabled;
	private Game game;
	
	//TODO fill your code
	public Ufo(AlienManager alienManager, Position position){
		super(alienManager, position);
		this.dir = Move.LEFT;
		this.symbol = 'U';
	}

	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();
		}
	}
	
	private void enable() {
	}

//	public void remove(){
//		this.enabled = false;
//	}

	public void automaticMove() {
		super.automaticMove();
	}



	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return game.getRandom().nextDouble() < game.getLevel().ufoFrequency;
	}
	
}
