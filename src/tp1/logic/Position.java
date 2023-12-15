package tp1.logic;

import tp1.control.exceptions.OffWorldException;
import tp1.view.Messages;

/**
 * Container class for position
 * Provides methods for simple interactions with positions.
 *
 * @param col final instance attributes
 */
public record Position(int col, int row) {

	/**
	 * Static Position factory with on-board validation
	 *
	 * @param col the x-axis position
	 * @param row the y-axis position
	 * @return Position instance
	 * @throws OffWorldException if position is out of game bounds
	 */
	public static Position create(int col, int row) throws OffWorldException {
		Position pos = new Position(col, row);

		if (pos.outBoundsX() || pos.outBoundsY())
			throw new OffWorldException(pos);

		return pos;
	}

	/**
	 * Position constructor
	 *
	 * @param col the x-axis position
	 * @param row the y-axis position
	 */
	public Position {
	}

	/**
	 * Return new position based on the current position and Move
	 *
	 * @param move the direction of movement
	 * @return the updated position
	 */
	public Position move(Move move) {
		return new Position(
				col + move.getX(),
				row + move.getY()
		);
	}

	/**
	 * Return new position based on the current position and Move
	 *
	 * @param move the direction of movement
	 * @return the updated position
	 * @throws OffWorldException if position is out of game bounds
	 */
	public Position validMove(Move move) throws OffWorldException {
		Position pos = this.move(move);
		if (pos.outBoundsX() || pos.outBoundsY())
			throw new OffWorldException(pos);

		return pos;
	}

	/**
	 * Compare two positions
	 *
	 * @param other position
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Position other) {
		return col == other.col && row == other.row;
	}

	/**
	 * Compare position with col and row as integers
	 *
	 * @param col x-axis
	 * @param row y-axis
	 * @return true if equal, false otherwise
	 */
	public boolean equals(int col, int row) {
		return this.equals(new Position(col, row));
	}

	/**
	 * Check if current position is located in specified row
	 *
	 * @param row the row to be checked
	 * @return true if is in row, false otherwise
	 */
	public boolean inRow(int row) {
		return this.row == row;
	}

	/**
	 * Check if current position is located in specified column
	 *
	 * @param col the column to be checked
	 * @return true if is in column, false otherwise
	 */
	public boolean inCol(int col) {
		return this.col == col;
	}


	/**
	 * for checking if the position is out of the X-axis bound
	 * @return true if in bounds
	 */
	public boolean outBoundsX() {
		return col < 0 || col >= Game.DIM_X;
	}

	/**
	 * for checking if the position + move is out of the X-axis bound
	 * @param move the move to perform before checking
	 * @return true if in bounds
	 */
	public boolean outBoundsX(Move move) {
		return this.move(move).outBoundsX();
	}

	/**
	 * for checking if the position is out of the Y-axis bound
	 * @return true if in bounds
	 */
	public boolean outBoundsY() {
		return col < 0 || col >= Game.DIM_X;
	}

	/**
	 * for checking if the position + move is out of the Y-axis bound
	 * @param move the move to perform before checking
	 * @return true if in bounds
	 */
	public boolean outBoundsY(Move move) {
		return this.move(move).outBoundsY();
	}

	/**
	 * @return String representative of this position
	 */
	@Override
	public String toString() {
		return Messages.POSITION.formatted(col, row);
	}

}
