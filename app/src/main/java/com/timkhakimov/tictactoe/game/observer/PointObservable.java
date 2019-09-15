package com.timkhakimov.tictactoe.game.observer;

/**
 * Created by Timur Khakimov on 15.09.2019
 */
public interface PointObservable {
    void addObserver(PointObserver observer);

    void removeObserver(PointObserver observer);

    void notifyObservers(int row, int column);
}
