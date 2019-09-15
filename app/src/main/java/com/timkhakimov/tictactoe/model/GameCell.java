package com.timkhakimov.tictactoe.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.Point;
import com.timkhakimov.tictactoe.game.observer.PointObserver;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Timur Khakimov on 15.09.2019
 * cell info - model class for recyclerview adapter
 */
public class GameCell extends BaseObservable implements Point, PointObserver {

    private Game game;
    private int row;
    private int column;

    public GameCell(Game game, int row, int column) {
        this.game = game;
        this.row = row;
        this.column = column;
        game.addObserver(this);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Bindable
    @Nullable
    public Player getPlayerMark() {
        return game.getBoard()[row][column].getPlayerMark();
    }

    @Bindable
    public boolean isAvailableForPlayer() {
        return !game.isFinished() && getPlayerMark() == null;
    }

    public void markCell() {
        if (game.isFinished()) {
            return;
        }
        game.markCell(row, column);
    }

    @Override
    public void update(int row, int column) {
        if (row == getRow() && column == getColumn()) {
            // TODO: 15.09.2019 notify BR variables
        }
    }

    public void removeCellObserver() {
        game.removeObserver(this);
    }
}
