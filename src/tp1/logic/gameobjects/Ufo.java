package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo{
	public static final String SYMBOL = Messages.UFO_SYMBOL;
	public static final int SCORE = 25;
	private final Game game;
	private int health = 1;
	private Position position;
	private final Move dir;
	
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

	public void computerAction() {
		this.automaticMove();
		if (Game.isOutOfBoundX(this.position)){
			game.disableUfo();
		}
	}

	public void remove(){
		this.game.disableUfo();
	}

	public String getSymbol() {
		return SYMBOL + "[" + this.health + "]";
	}
}
