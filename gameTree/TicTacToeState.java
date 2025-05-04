

import java.util.ArrayList;

/**
* Game State for TicTacToe.
* @author huan zhou
* @version 1
*/
public class TicTacToeState implements State {

    String[][] board;
    boolean playerTurn;

    /**
     * Default constructor. Creates a starting game state.
     * Computer will be X, and player will be O.
     *
     * @param turn Indicates whether it is the player's turn first.
     */
    public TicTacToeState(boolean turn) {
        board = new String[3][3];
        this.playerTurn = turn;
    }


    /**
     * Copy constructor. Creates a new state by
     * copying the values in the board parameter.
     * Computer will be X, and player will be O.
     *
     * @param board The current game board to be copied.
     * @param turn Indicates whether it is the player's turn in this state.
     */
    public TicTacToeState(String[][] board, boolean turn) {
        this.board = new String[3][3];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                this.board[r][c] = board[r][c];
            }
        }

        this.playerTurn = turn;
    }

    /**
     * Returns the mark for the player whose turn it is in this state.
     *
     * @return "O" if playerTurn is true, "X" otherwise.
     */
    public String getMark() {
        return playerTurn ? "O" : "X";
    }

    /**
     * Returns the board for this state.
     *
     * @return The board.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Returns whether it is the human player's turn or not.
     *
     * @return true if it is the human player's turn. (The current turn is "O".)
     */
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    /**
     * Returns a string representation of this state.
     *
     * @return The string representing this state.
     */
    public String toString() {
        String ret = "";
        String bar = " -------------\n";
        ret += bar;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == null) {
                    ret += " |  ";
                } else {
                    ret += " | " + board[r][c];
                }
            }
            ret += " |\n";
            ret += bar;
        }

        return ret;
    }

    /**
     * Finds all legal moves from the current state.
     *
     * @return ArrayList of moves.
     */
    public ArrayList<State.Move> findAllMoves() {
        ArrayList<State.Move> moves = new ArrayList<State.Move>();
        
        // Check all locations on the board
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                // If the current position is empty (null), it's a valid move
                if (board[r][c] == null) {
                    moves.add(new Move(r, c));
                }
            }
        }
        
        return moves;
    }

    /**
     * Tests whether the game is over.
     *
     * @return true if game is over, false otherwise.
     */
    public boolean gameOver() {
        // Check if either player has won
        if (hasWon("X") || hasWon("O")) {
            return true;
        }
        
        // Check if the board is full
        return isBoardFull();
    }

    /**
     * Helper method to check if the board is full.
     * 
     * @return true if the board is full, false otherwise.
     */
    private boolean isBoardFull() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method to check if a player has won.
     * 
     * @param mark The player's mark ("X" or "O").
     * @return true if the player has won, false otherwise.
     */
    private boolean hasWon(String mark) {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != null && board[r][0].equals(mark) 
                && board[r][1] != null && board[r][1].equals(mark) 
                && board[r][2] != null && board[r][2].equals(mark)) {
                return true;
            }
        }
        
        // Check columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != null && board[0][c].equals(mark) 
                && board[1][c] != null && board[1][c].equals(mark) 
                && board[2][c] != null && board[2][c].equals(mark)) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] != null && board[0][0].equals(mark) 
            && board[1][1] != null && board[1][1].equals(mark) 
            && board[2][2] != null && board[2][2].equals(mark)) {
            return true;
        }
        
        if (board[0][2] != null && board[0][2].equals(mark) 
            && board[1][1] != null && board[1][1].equals(mark) 
            && board[2][0] != null && board[2][0].equals(mark)) {
            return true;
        }
        
        return false;
    }

    /**
     * Returns the value of an end-game state. Throws a new IllegalStateException if the
     * current state is not an end-game.
     *
     * @return 1 for a win for X, -1 for a loss.
     */
    public int getValue() {
        // Check if the game is over
        if (!gameOver()) {
            throw new IllegalStateException("Cannot evaluate a non-end-game state");
        }
        
        // Check if X has won
        if (hasWon("X")) {
            return 1;  // X wins
        }
        
        // Check if O has won
        if (hasWon("O")) {
            return -1; // O wins (loss for X)
        }
        
        // If nobody has won, it's a draw
        return 0;
    }

    /**
     * Tests whether a move is legal and performs it if so.
     *
     * @param m Move The move to be done.
     * @return true if move was legal, false otherwise.
     */
    public boolean doMove(State.Move m) {
        // Cast the move to TicTacToeState.Move
        Move move = (Move) m;
        int r = move.r;
        int c = move.c;
        
        // Check if the move is valid (within bounds and position is empty)
        if (r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == null) {
            // Set the board position to the current player's mark
            board[r][c] = getMark();
            
            // Switch player turn
            playerTurn = !playerTurn;
            
            return true;
        }
        
        return false;
    }

    /**
     * Undoes the effects of the given move.
     *
     * @param m Move The move to be undone.
     */
    public void undoMove(State.Move m) {
        // Cast the move to TicTacToeState.Move
        Move move = (Move) m;
        int r = move.r;
        int c = move.c;
        
        // Reset the board position to null
        board[r][c] = null;
        
        // Switch back the player turn
        playerTurn = !playerTurn;
    }

    /**
     * Inner class to represent a move in Tic-tac-toe.
     */
    public class Move implements State.Move {

        int r;
        int c;

        /**
         * Default constructor.
         * @param r Radius
         * @param c Column
         */
        public Move(int r, int c) {
            this.r = r;
            this.c = c;
        }

        /**
         * Returns a string representation of this move.
         *
         * @return The string representing this move.
         */
        public String toString() {
            return "row " + r + " column " + c;
        }

        /**
         * Determine whether this move is equal to another object.
         *
         * @param o Object for comparing
         * @return true if all data from the move matches, false otherwise.
         */
        public boolean equals(Object o) {
            if (o instanceof Move) {
                Move m = (Move) o;

                return m.r == r && m.c == c;
            }

            return false;
        }
    }
}