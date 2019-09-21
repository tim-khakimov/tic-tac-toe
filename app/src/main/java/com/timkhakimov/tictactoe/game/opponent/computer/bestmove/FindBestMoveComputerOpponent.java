package com.timkhakimov.tictactoe.game.opponent.computer.bestmove;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.GameUtils;
import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.opponent.computer.AbstractComputerOpponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur Khakimov on 21.09.2019
 * computer opponent with finding best move using minimax algorithm
 */
public class FindBestMoveComputerOpponent extends AbstractComputerOpponent {

    public FindBestMoveComputerOpponent(Game game, Player player) {
        super(game, player);
    }

    @Override
    protected void calculateNextMove(Cell[][] board) {
        List<PointWithResult> pointWithResults = calculatePointsWithResultsUsingMinimax(board, getPlayer());
        PointWithResult bestMove = getPointWithBestResult(pointWithResults, getPlayer());
        markCell(bestMove.getRow(), bestMove.getColumn());
    }

    private static PointWithResult getPointWithBestResult(List<PointWithResult> pointWithResults, Player player) {
        PointWithResult pointWithResult = null;
        for (PointWithResult point : pointWithResults) {
            if(pointWithResult == null || isOtherScoresPreferable(player, pointWithResult.result, point.result)) {
                pointWithResult = point;
            }
        }
        return pointWithResult;
    }

    private static List<PointWithResult> calculatePointsWithResultsUsingMinimax(Cell[][] board, Player player) {
        List<PointWithResult> points = getEmptyPoints(board);
        for (PointWithResult point : points) {
            fillPointWithResult(board, player, point, points.size());
        }
        return points;
    }

    private static void fillPointWithResult(Cell[][] board, Player player, PointWithResult point, int emptyPointsSize) {
        int moveScores = getScoresSumForMove(board, player, point.getRow(), point.getColumn(), emptyPointsSize);
        board[point.getRow()][point.getColumn()].setPlayerMark(null);
        point.result = moveScores;
    }

    private static int getScoresSumForMove(Cell[][] board, Player player, int row, int column, int emptyPointsCount) {
        board[row][column].setPlayerMark(player);
        if(GameUtils.isPlayerWon(board, player, row, column)) {
            return player == Player.CROSS ? getWinningScoresMultiplier(emptyPointsCount) : (-1 * getWinningScoresMultiplier(emptyPointsCount));
        }
        if(GameUtils.isBoardFilled(board)) {
            return 0;
        }
        List<PointWithResult> opponentsPointsWithResult = calculatePointsWithResultsUsingMinimax(board, Player.getOther(player));
        int allScores = 0;
        for (PointWithResult pointWithResult : opponentsPointsWithResult) {
            allScores += pointWithResult.result;
        }
        return allScores;
    }

    private static int getWinningScoresMultiplier(int emptyPointsCount) {
        if(emptyPointsCount == 0) {
            return 0;
        }
        return 1 + (emptyPointsCount - 1) * getWinningScoresMultiplier(emptyPointsCount - 1);
    }

    private static boolean isOtherScoresPreferable(Player player, int currentScores, int otherScores) {
        if(player == Player.CROSS) {
            return otherScores > currentScores;
        }
        return otherScores < currentScores;
    }

    private static List<PointWithResult> getEmptyPoints(Cell[][] board) {
        List<PointWithResult> points = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if(board[row][column].isEmpty()) {
                    points.add(new PointWithResult(row, column, 0));
                }
            }
        }
        return points;
    }
}
