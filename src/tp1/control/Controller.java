package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMLaser;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		printGame();

		beginning: while(true) {
			String[] prompt = prompt();
			switch (prompt[0].charAt(0)) {
				case 'm': // move [direction]
					Move move = switch (prompt[1]) {
						case "left" -> Move.LEFT;
						case "lleft" -> Move.LLEFT;
						case "right" -> Move.RIGHT;
						case "rright" -> Move.RRIGHT;
						default -> Move.NONE;
					};
					Position position = this.game.UCMship.position;
					boolean isOutOfBounds = this.game.isOutOfBoundX(position.move(move));
					if(!isOutOfBounds) this.game.UCMship.preformMovement(move);
					else  {
						System.out.println("Movement cannot be preformed");
						continue beginning;
					}

					break;
				case 's': // shoot
					this.game.UCMship.preformAttack(game);
					break;
				case 'n': // none
					return;
			}

			this.game.alienManager.automaticMove();

			UCMLaser laser = this.game.UCMship.getLaser();
			if(laser != null) {
				boolean isHit = false;
				for (RegularAlien alien: this.game.alienManager.regularAliens) {
					isHit = laser.weaponAttack(alien);
					if(isHit) break;
				}
				if(!isHit) laser.automaticMove();
			}

			printGame();
		}

		//TODO fill your code
	}

	/**
	 * Draw / paint the game
	 */
	private void printGame() {
		System.out.println(printer);
	}
	
	/**
	 * Prints the final message once the game is finished
	 */
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	
}
