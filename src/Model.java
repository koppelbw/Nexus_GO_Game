/* *****************************************************************************
 * Project: Nexus_GO_Game.java
 * File: Model.java
 * Description: The Model class performs all back-end functionality for the game.
 * Author: William Koppelberger
 * Date: 2/2/15
 * *****************************************************************************/
public class Model {

	/* Variable used for the game */
	private int row;
	private int col;
	private char board[][];
	private char playerTurn;
	private int numMoves;

	/* Constructor [row, col] */
	Model(int row, int col) {
		this.row = row;
		this.col = col;
		board = new char[row][col];
		playerTurn = 'B';
		numMoves = 0;

		reset();
	}

	/* Getter methods for variable access */
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public char getPlayerTurn() {
		return playerTurn;
	}

	public char[][] getBoard() {
		return board;
	}

	/* Advances game one turn */
	public void updatePlayerTurn(int xPos, int yPos, char player) {

		if ((board[xPos][yPos] == ' ') && (numMoves < (row * col))) {

			board[xPos][yPos] = player;

			if (player == 'B') {
				board[xPos][yPos] = playerTurn;
				playerTurn = 'W';
			} else {
				board[xPos][yPos] = playerTurn;
				playerTurn = 'B';
			}

			numMoves++;
		}
	}

	/* Resets the game */
	public void reset() {
		playerTurn = 'B';
		numMoves = 0;

		for (int r = 0; r < row; r++)
			for (int c = 0; c < row; c++)
				board[r][c] = ' ';
	}

	/* Check if position being checked will be off the board */
	public char pos(int x, int y) {
		if ((x < 0) || (y < 0) || (y >= col) || (x >= row))
			return '%';
		else
			return board[x][y];
	}

	/* Check Row Winner */
	public boolean checkRow() {

		// Check Rows
		for (int r = 0; r < row; r++)
			for (int c = 0; c < row; c++)
				if (pos(r, c) != 0)
					if (pos(r, c) == pos(r, c + 1)
							&& pos(r, c) == pos(r, c + 2)
							&& pos(r, c) == pos(r, c + 3)
							&& pos(r, c) == pos(r, c + 4) && board[r][c] != ' ') {
						if (playerTurn == 'B') {
							playerTurn = 'W';
						} else {
							playerTurn = 'B';
						}
						numMoves = row * col;
						return true;
					}

		return false;
	}

	/* Check Column Winner */
	public boolean checkCol() {
		// Check Columns

		for (int r = 0; r < row; r++)
			for (int c = 0; c < row; c++)
				if (pos(r, c) != 0)
					if (pos(r, c) == pos(r + 1, c)
							&& pos(r, c) == pos(r + 2, c)
							&& pos(r, c) == pos(r + 3, c)
							&& pos(r, c) == pos(r + 4, c) && board[r][c] != ' ') {

						if (playerTurn == 'B')
							playerTurn = 'W';
						else
							playerTurn = 'B';

						numMoves = row * col;
						return true;
					}

		return false;
	}

	/* Check Diagonal Winners */
	public boolean checkDiagonals() {
		for (int r = 0; r < row; r++)
			for (int c = 0; c < row; c++)
				for (int d = -1; d <= 1; d += 2) {
					if (pos(r, c) != 0)
						if (pos(r, c) == pos(r + 1 * d, c + 1)
								&& pos(r, c) == pos(r + 2 * d, c + 2)
								&& pos(r, c) == pos(r + 3 * d, c + 3)
								&& pos(r, c) == pos(r + 4 * d, c + 4)
								&& board[r][c] != ' ') {

							if (playerTurn == 'B')
								playerTurn = 'W';
							else
								playerTurn = 'B';

							numMoves = row * col;
							return true;
						}
				}

		return false;
	}

	/* Check if player has won */
	public char isWinner() {

		// Check Winner
		if (checkRow())
			return 'W';
		if (checkCol())
			return 'W';
		if (checkDiagonals())
			return 'W';

		// Check Tie Game
		if (numMoves >= row * col) {
			numMoves++;
			return 'T';
		}

		return 'N';
	}
}