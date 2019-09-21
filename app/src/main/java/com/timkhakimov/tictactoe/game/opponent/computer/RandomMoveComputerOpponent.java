package com.timkhakimov.tictactoe.game.opponent.computer;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.Point;
import com.timkhakimov.tictactoe.game.model.SimplePoint;
import com.timkhakimov.tictactoe.game.opponent.Opponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur Khakimov on 21.09.2019
 * simple computer opponent with random moving
 */
public class RandomMoveComputerOpponent implements Opponent {

    private Game game;
    private Player player;

    public RandomMoveComputerOpponent(Game game, Player player) {
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
            randomMove();
        }
    }

    private void randomMove() {
        List<Point> points = getEmptyPointsFromBoard(game.getBoard());
        Point randomPoint = points.get((int)(Math.random() * points.size()));
        markCell(randomPoint.getRow(), randomPoint.getColumn());
    }

    private List<Point> getEmptyPointsFromBoard(Cell[][] board) {
        List<Point> points = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if(board[row][column].getPlayerMark() == null) {
                    points.add(new SimplePoint(row, column));
                }
            }
        }
        return points;
    }
}
