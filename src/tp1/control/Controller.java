package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.gameobjects.Alien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;
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
	 * Runs the game logic.
	 */
	public void run() {
		printGame();

		beginning: while(true) {
			if(game.playerWin() || game.aliensWin()) {
				printEndMessage();
				return;
			}

			String[] prompt = prompt();
			switch (prompt[0].charAt(0)) {
				// move [direction]
				case 'm':
					Move move = switch (prompt[1]) {
						case "left" -> Move.LEFT;
						case "lleft" -> Move.LLEFT;
						case "right" -> Move.RIGHT;
						case "rright" -> Move.RRIGHT;
						default -> Move.NONE;
					};
					// move the ship and if return false -> ship couldn't be moved
					if(!this.game.moveShip(move)) {
						System.out.println("Movement cannot be performed");
						continue beginning;
					}
					break;

				// shoot
				case 's':
					this.game.enableLaser();
					break;

				// none
				case 'n':
					continue beginning;

				// ask for help
				case 'h':
					System.out.println("Available commands:");
					System.out.println("[m]ove <left|lleft|right|rright>: moves the UCMShip to the indicated direction");
					System.out.println("[s]hoot: player shoots a laser");
					System.out.println("shock[W]ave: player releases a shock wave");
					System.out.println("[l]ist: print the list of current ships");
					System.out.println("[r]eset: start a new game");
					System.out.println("[h]elp: print this help message");
					System.out.println("[e]xit: end the execution of the game");
					System.out.println("[n]one | '' '' : skips cycle");
					continue beginning;

				// displays the list of objects
				case 'l':
					System.out.println("[U]CM Ship: damage = 1, endurance = 3");
					System.out.println("[R]egular Alien: points , damage = 1, endurance = 1" );
					System.out.println("[D]estroyer Alien: points='10', damage='1', endurance='1'");
					System.out.println("U[f]o: points='25', damage='0', endurance='1'");
					// System.out.println(this.game.level.printGameAttributes());
					continue beginning;

				// reset
				case 'r':
					this.game.resetGame();
					printGame();
					continue beginning;

				// end
				case 'e':
					printEndMessage();
					return;
			}

			this.game.performCycle();
			printGame();
		}
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
