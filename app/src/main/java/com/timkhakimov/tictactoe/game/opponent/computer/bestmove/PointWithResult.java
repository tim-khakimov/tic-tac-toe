package com.timkhakimov.tictactoe.game.opponent.computer.bestmove;

import com.timkhakimov.tictactoe.game.model.Point;

/**
 * Created by Timur Khakimov on 21.09.2019
 * point with calculated scores for moving to this point
 */
public class PointWithResult implements Point {

    private int row;
    private int column;
    public int result;

    public PointWithResult(int row, int column, int result) {
        this.row = row;
        this.column = column;
        this.result = result;
    }

    public void setData(int row, int column, int result) {
        this.row = row;
        this.column = column;
        this.result = result;
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
