package com.timkhakimov.tictactoe.game;

import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.PlayerMove;
import com.timkhakimov.tictactoe.game.model.Point;

import java.util.Stack;

/**
 * Created by Timur Khakimov on 10.09.2019
 * current game
 */
public class Game {

    private Cell[][] board;
    private Stack<Point> movesStack;

    public Game(int size) {
        board = GameUtils.createBoard(size);
        movesStack = new Stack<>();
    }

    public void markCell(Player player, int row, int column) {
        board[row][column].setPlayerMark(player);
        movesStack.push(new PlayerMove(player, row, column));
    }

    public void cancelLastMove() {
        if(movesStack.isEmpty()) {
            return;
        }
        Point lastMovePoint = movesStack.pop();
        board[lastMovePoint.getRow()][lastMovePoint.getColumn()].setPlayerMark(null);
    }
}
