package jabadot;

import java.util.*;

/** The logic for maintaining a game, two (human?) players.
 * This class is independant of any view logic - keep it so!
 */
public class ChessGame {
	public static final char NONE = ' ';
	public static final char KW = 'K';
	public static final char QW = 'Q';
	public static final char BW = 'B';
	public static final char RW = 'R';
	public static final char NW = 'K';
	public static final char PW = 'P';
	public static final char KB = 'k';
	public static final char QB = 'q';
	public static final char BB = 'b';
	public static final char RB = 'r';
	public static final char NB = 'k';
	public static final char PB = 'p';
	protected User white;
	protected User black;
	ArrayList moves;
	public static final short WNEXT = 0;
	public static final short BNEXT = 1;
	public short nextMove = WNEXT;
	public char board[][] = new char[8][8];


	public ChessGame() {
		moves = new ArrayList();
		initBoard();
	}

	protected void initBoard() {
		// white
		board[0][0] = RW;
		board[0][1] = KW;
		board[0][2] = BW;
		board[0][3] = QW;
		board[0][4] = KW;
		board[0][5] = BW;
		board[0][6] = KW;
		board[0][7] = RW;
		for (int i=0; i<8; i++)
			board[1][i] = PW;

		// vacant
		for (int r=2; r<=5; r++)
			for (int c=0; c<8; c++)
				board[r][c] = NONE;

		// black
		for (int i=0; i<8; i++)
			board[6][i] = PB;
		board[7][0] = RB;
		board[7][1] = KB;
		board[7][2] = BB;
		board[7][3] = QB;
		board[7][4] = KB;
		board[7][5] = BB;
		board[7][6] = KB;
		board[7][7] = RB;
	}
	
	/** Accept a move  in standard "kp4" notation */
	public void move(String move) throws ChessMoveException {
	}

	// Need to write more methods...
	// getters, setters, etc.
}
