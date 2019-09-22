package com.timkhakimov.tictactoe.game.opponent.computer.bestmove;

import com.timkhakimov.tictactoe.game.model.Point;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Timur Khakimov on 22.09.2019
 */
public abstract class MoveObserver implements Observer<Point> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
