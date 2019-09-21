package com.timkhakimov.tictactoe.game.opponent.computer;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.opponent.Opponent;

/**
 * Created by Timur Khakimov on 21.09.2019
 * abstract class for computer opponents
 */
public abstract class AbstractComputerOpponent implements Opponent {

    protected Game game;
    protected Player player;

    public AbstractComputerOpponent(Game game, Player player) {
        this.game = game;
        this.player = player;
        game.addObserver(this);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void markCell(int row, int column) {
        game.markCell(row, column);
    }

    @Override
    public void update(int row, int column) {
        if(!game.isFinished() && game.getCurrentPlayer() == getPlayer()) {
            calculateNextMove(game.getBoard());
        }
    }

    protected abstract void calculateNextMove(Cell[][] board);
}
