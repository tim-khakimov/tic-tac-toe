package com.timkhakimov.tictactoe.game.model;

/**
 * Created by Timur Khakimov on 10.09.2019
 * player moving info
 */
public class PlayerMove implements Point {

    private Player player;
    private int row;
    private int column;

    public PlayerMove(Player player, int row, int column) {
        this.player = player;
        this.row = row;
        this.column = column;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
