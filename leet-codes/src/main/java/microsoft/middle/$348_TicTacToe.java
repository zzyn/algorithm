package microsoft.middle;
/*
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
Follow up:
Could you do better than O(n2) per move() operation?

 */
public class $348_TicTacToe {

    int[] rows, cols, dig;
    int n;

    /**
     * Initialize your data structure here.
     */
    public $348_TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        dig = new int[2];
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        return (
                (player == 1
                        && rows[row]++ == n - 1
                        | cols[col]++ == n - 1
                        | (row == col && dig[0]++ == n - 1)
                        | (row + col == n - 1 && dig[1]++ == n - 1)
                ) ||
                        (player == 2
                                && rows[row]-- == 1 - n
                                | cols[col]-- == 1 - n
                                | (row == col && dig[0]-- == 1 - n)
                                | (row + col == n - 1 && dig[1]-- == 1 - n)
                        )
        ) ? player : 0;
    }
}
