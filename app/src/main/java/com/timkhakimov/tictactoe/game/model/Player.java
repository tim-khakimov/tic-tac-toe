package com.timkhakimov.tictactoe.game.model;

import android.support.annotation.NonNull;

/**
 * Created by Timur Khakimov on 10.09.2019
 */
public enum Player {
    NOUGHT,
    CROSS;

    @NonNull
    public static Player getOther(@NonNull Player player) {
        return player == NOUGHT ? CROSS : NOUGHT;
    }
}
