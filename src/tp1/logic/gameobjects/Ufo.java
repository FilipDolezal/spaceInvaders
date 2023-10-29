package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;


public class Ufo{
	//TODO fill your code

	private boolean enabled = false;
	private Game game;
	private int health = 1;
	public static final String SYMBOL = "U";
	public final int SCORE = 25;
	private Position position;
	private Move dir;
	
	//TODO fill your code
	public Ufo(Game game) {
		this.game = game;
		this.position = new Position(Game.DIM_X, 0);
		this.dir = Move.LEFT;
	}
	public void performMovement(Move move) {
		this.position = position.move(move);
	}
	public void automaticMove() {
		this.performMovement(dir);
	}
	public boolean receiveAttack() {
		if(--this.health == 0) {
			this.remove();
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return this.position;
	}

	public int getHealth() {
		return this.health;
	}
	public void computerAction() {
		if(!isEnabled() && canGenerateRandomUfo()) {
			enable();
		}
		if (isEnabled()) {
			automaticMove();
			if (Game.isOutOfBoundX(this.position)){
				remove();
			}
		}
	}

	private void enable() {
		if(!this.enabled)
			enabled = true;
	}

	public void remove(){
		this.enabled = false;
	}



	public boolean isEnabled() {
		return enabled;
	}
	public String getSymbol() {
		return SYMBOL + "[" + this.health + "]";
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
