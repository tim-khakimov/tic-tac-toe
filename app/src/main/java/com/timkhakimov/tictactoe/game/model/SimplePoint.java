package com.timkhakimov.tictactoe.game.model;

/**
 * Created by Timur Khakimov on 21.09.2019
 * simple class with point info
 */
public class SimplePoint implements Point {

    private int row;
    private int column;

    public SimplePoint(int row, int column) {
        this.row = row;
        this.column = column;
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
