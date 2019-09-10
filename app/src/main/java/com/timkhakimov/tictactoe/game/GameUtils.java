package com.timkhakimov.tictactoe.game;

import com.timkhakimov.tictactoe.game.model.Cell;

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
}
