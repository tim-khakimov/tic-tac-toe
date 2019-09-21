package com.timkhakimov.tictactoe.game.opponent.computer;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.GameUtils;
import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.Point;

import java.util.List;

/**
 * Created by Timur Khakimov on 21.09.2019
 * simple computer opponent with random moving
 */
public class RandomMoveComputerOpponent extends AbstractComputerOpponent {

    public RandomMoveComputerOpponent(Game game, Player player) {
        super(game, player);
    }

    @Override
    protected void calculateNextMove(Cell[][] board) {
        List<Point> emptyPoints = GameUtils.getEmptyPointsFromBoard(board);
        Point randomPoint = emptyPoints.get((int) (Math.random() * emptyPoints.size()));
        markCell(randomPoint.getRow(), randomPoint.getColumn());
    }
}
