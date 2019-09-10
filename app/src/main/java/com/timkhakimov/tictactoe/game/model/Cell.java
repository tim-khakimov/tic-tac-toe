package com.timkhakimov.tictactoe.game.model;

import android.support.annotation.Nullable;

/**
 * Created by Timur Khakimov on 10.09.2019
 * cell on game board
 */
public class Cell {

    @Nullable
    private Player playerMark;

    @Nullable
    public Player getPlayerMark() {
        return playerMark;
    }

    public void setPlayerMark(@Nullable Player playerMark) {
        this.playerMark = playerMark;
    }

    public boolean isEmpty() {
        return playerMark == null;
    }
}
