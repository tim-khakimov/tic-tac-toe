package com.timkhakimov.tictactoe.game.opponent.computer.bestmove;

import android.support.annotation.NonNull;

import com.timkhakimov.tictactoe.game.model.Point;

/**
 * Created by Timur Khakimov on 21.09.2019
 * point with calculated scores for moving to this point
 */
public class PointWithResult implements Point {

    @NonNull
    private Point point;
    public int result;

    public PointWithResult(@NonNull Point point) {
        this.point = point;
    }

    public PointWithResult(@NonNull Point point, int result) {
        this.point = point;
        this.result = result;
    }

    @Override
    public int getRow() {
        return point.getRow();
    }

    @Override
    public int getColumn() {
        return point.getColumn();
    }
}
