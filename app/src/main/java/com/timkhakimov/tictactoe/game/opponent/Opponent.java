package com.timkhakimov.tictactoe.game.opponent;

import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.observer.PointObserver;

/**
 * Created by Timur Khakimov on 21.09.2019
 * common interface for all opponents (computer, remote etc)
 */
public interface Opponent extends PointObserver {
    Player getPlayer();
    void markCell(int row, int column);
}
