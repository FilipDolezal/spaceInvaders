package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
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
		while(true) {
			printGame();
			String[] prompt = prompt();
			switch (prompt[0].charAt(0)) {
				case 'm': // move [direction]
					this.game.UCMship.preformMovement(
							switch (prompt[1]) {
								case "left" -> Move.LEFT;
								case "lleft" -> Move.LLEFT;
								case "right" -> Move.RIGHT;
								case "rright" -> Move.RRIGHT;
								default -> Move.NONE;
							}
					);
					break;
				case 's': // shoot
					this.game.UCMship.preformAttack();
					break;
				case 'n': // none
					return;
			}

			UCMLaser laser = this.game.UCMship.getLaser();
			if(laser != null) {
				laser.automaticMove();
			}
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
