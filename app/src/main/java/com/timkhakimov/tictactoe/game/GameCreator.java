package com.timkhakimov.tictactoe.game;

/**
 * Created by Timur Khakimov on 18.09.2019
 * game creator
 */
public class GameCreator {

    private int boardSize;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Game create() {
        Game game = new Game(GameUtils.createBoard(boardSize));
        clear();
        return game;
    }

    private void clear() {
        boardSize = 0;
    }
}
