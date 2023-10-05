package tp1.logic;

/**
 *
 * Immutable class to encapsulate and manipulate positions in the game board
 *
 */
public class Position {

	public final int col;
	public final int row;

	//TODO fill your code
	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public Position move(Move move) {
		return new Position(
				col + move.getX(),
				row + move.getY()
		);
	}

	public boolean equals(Position other) {
		return col == other.col && row == other.row;
	}

	public boolean equals(int col, int row) {
		return this.equals(new Position(col, row));
	}

	public String toString() {
		return "col: "+ col + "| row: " + row;
	}

}
