package com.timkhakimov.tictactoe.game;

import android.support.annotation.NonNull;

import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.PlayerMove;
import com.timkhakimov.tictactoe.game.model.Point;
import com.timkhakimov.tictactoe.game.observer.PointObservable;
import com.timkhakimov.tictactoe.game.observer.PointObserver;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Timur Khakimov on 10.09.2019
 * current game
 */
public class Game implements PointObservable {

    @NonNull
    private Cell[][] board;
    private Stack<Point> movesStack;
    private boolean isFinished = false;
    private boolean isDraw = false;
    private List<PointObserver> observers;

    public Game(@NonNull Cell[][] board) {
        this.board = board;
        movesStack = new Stack<>();
        observers = new ArrayList<>();
    }

    public void markCell(int row, int column) {
        Player player = getCurrentPlayer();
        board[row][column].setPlayerMark(player);
        movesStack.push(new PlayerMove(player, row, column));
        if(GameUtils.isPlayerWon(board, player, row, column)) {
            isFinished = true;
            isDraw = false;
        } else if(movesStack.size() == board.length * board[0].length) {
            isFinished = true;
            isDraw = true;
        }
        notifyObservers(row, column);
    }

    public void undoLastMove() {
        if (movesStack.isEmpty()) {
            return;
        }
        isFinished = false;
        isDraw = false;
        Point lastMovePoint = movesStack.pop();
        board[lastMovePoint.getRow()][lastMovePoint.getColumn()].setPlayerMark(null);
        notifyObservers(lastMovePoint.getRow(), lastMovePoint.getColumn());
    }

    public Player getCurrentPlayer() {
        return movesStack.size() % 2 == 0 ? Player.CROSS : Player.NOUGHT;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Nullable
    public Player getWinner() {
        if (!isFinished || isDraw) {
            return null;
        }
        return getCurrentPlayer() == Player.CROSS ? Player.NOUGHT : Player.CROSS;
    }

    @Override
    public void addObserver(PointObserver observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(PointObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int row, int column) {
        for (PointObserver observer : observers) {
            observer.update(row, column);
        }
    }
}
