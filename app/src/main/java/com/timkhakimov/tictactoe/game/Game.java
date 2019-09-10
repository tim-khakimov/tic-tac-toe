package com.timkhakimov.tictactoe.game;

import com.timkhakimov.tictactoe.game.model.Cell;

/**
 * Created by Timur Khakimov on 10.09.2019
 * current game
 */
public class Game {

    private Cell[][] board;

    public Game(int size) {
        board = GameUtils.createBoard(size);
    }
}
