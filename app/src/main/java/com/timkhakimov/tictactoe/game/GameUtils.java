package com.timkhakimov.tictactoe.game;

import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;

/**
 * Created by Timur Khakimov on 10.09.2019
 * static methods for games
 */
public class GameUtils {

    public static Cell[][] createBoard(int size) {
        Cell[][] board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
        return board;
    }

    public static boolean isPlayerWon(Cell[][] board, Player player, int row, int column) {
        return isRowFilled(board, player, row) || isColumnFilled(board, player, column) ||
                isDiagonalFilled(board, player, row, column);
    }

    public static boolean isRowFilled(Cell[][] board, Player player, int row) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i].getPlayerMark() != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean isColumnFilled(Cell[][] board, Player player, int column) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][column].getPlayerMark() != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDiagonalFilled(Cell[][] board, Player player, int row, int column) {
        if (isCellInTopDownDiagonal(row, column)) {
            return isDiagonalFilled(board, player, true);
        }
        if (isCellInBottomUpDiagonal(board, row, column)) {
            return isDiagonalFilled(board, player, false);
        }
        return false;
    }

    public static boolean isDiagonalFilled(Cell[][] board, Player player, boolean isTopDown) {
        int row = isTopDown ? 0 : board.length - 1;
        int column = 0;
        while (column < board.length) {
            if (board[row][column].getPlayerMark() != player) {
                return false;
            }
            row += (isTopDown ? 1 : -1);
            column++;
        }
        return true;
    }

    public static boolean isCellInTopDownDiagonal(int row, int column) {
        return row == column;
    }

    public static boolean isCellInBottomUpDiagonal(Cell[][] board, int row, int column) {
        return row == (board.length - 1 - column);
    }
}
