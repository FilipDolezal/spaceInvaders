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
	 * Runs the game logic
	 */


	//Prompt gets the input from the user. Prompt[0].charAt(0) takes the first char of the input to know what the command will be.
	public void run() {
		printGame();

		beginning: while(true) {
			if(game.playerWin() || game.aliensWin()) {
				printEndMessage();
				return;
			}

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
					//Checks if the next movement is possible or not.
					boolean isOutOfBounds = this.game.isOutOfBoundX(position.move(move));
					if(!isOutOfBounds) this.game.UCMship.preformMovement(move);
					else  {
						System.out.println("Movement cannot be performed");
						continue beginning;
					}

					break;
				case 's': // shoot
					this.game.UCMship.preformAttack(game);
					break;
				case 'n': // none
					return;
				case 'h': // ask for help
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

				case 'l': // displays the list of objects; ask filip how to put the attributes of each thing.
					System.out.println("[U]CM Ship: damage = 1, endurance = 3");
					System.out.println("[R]egular Alien: points , damage = 1, endurance = 1" );
					System.out.println("[D]estroyer Alien: points='10', damage='1', endurance='1'");
					System.out.println("U[f]o: points='25', damage='0', endurance='1'");
					// System.out.println(this.game.level.printGameAttributes());

					continue beginning;

				case 'r':
					this.game.resetGame();
					printGame();
					continue beginning;

				case 'e': // end
					printEndMessage();
					System.exit(0);
			}

			this.game.alienManager.automaticMove();

			UCMLaser laser = this.game.UCMship.getLaser();
			if(laser != null) {
				boolean isHit = false;
				for (Alien alien: this.game.alienManager.getAliens()) {
					if(alien == null) continue;
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
