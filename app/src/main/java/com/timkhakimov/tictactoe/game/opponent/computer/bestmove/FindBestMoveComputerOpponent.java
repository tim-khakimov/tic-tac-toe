package com.timkhakimov.tictactoe.game.opponent.computer.bestmove;

import com.timkhakimov.tictactoe.game.Game;
import com.timkhakimov.tictactoe.game.GameUtils;
import com.timkhakimov.tictactoe.game.model.Cell;
import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.model.Point;
import com.timkhakimov.tictactoe.game.opponent.computer.AbstractComputerOpponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        List<Point> inputs = GameUtils.getEmptyPointsFromBoard(board);
        findBestVariantObservable(board, inputs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MoveObserver() {
                    @Override
                    public void onNext(Point point) {
                        markCell(point.getRow(), point.getColumn());
                    }
                });
    }

    private Observable<PointWithResult> findBestVariantObservable(Cell[][] board, List<Point> points) {
        return Observable.create(new ObservableOnSubscribe<PointWithResult>() {
            @Override
            public void subscribe(ObservableEmitter<PointWithResult> emitter){
                final int emptyPointsSize = points.size();
                Player player = getPlayer();
                int maxThreadsCount = Runtime.getRuntime().availableProcessors() - 1;
                final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(emptyPointsSize > maxThreadsCount ? maxThreadsCount : emptyPointsSize);
                try {
                    Iterable<PointWithResult> pointWithResultIterable  = Observable.<Point>fromIterable(points)
                            .flatMap((point) ->
                                    Observable.defer(() ->
                                            Observable.just(getPointWithResult(GameUtils.copyBoard(board), player, point, emptyPointsSize)))
                                            .subscribeOn(Schedulers.from(threadPoolExecutor)))
                            .blockingIterable();
                    emitter.onNext(getPointWithBestResult(pointWithResultIterable, player));
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
                threadPoolExecutor.shutdown();
            }
        });
    }

    private static PointWithResult getPointWithBestResult(Iterable<PointWithResult> pointWithResults, Player player) {
        PointWithResult pointWithResult = null;
        for (PointWithResult point : pointWithResults) {
            if(pointWithResult == null || isOtherScoresPreferable(player, pointWithResult.result, point.result)) {
                pointWithResult = point;
            }
        }
        return pointWithResult;
    }

    private static List<PointWithResult> calculatePointsWithResultsUsingMinimax(Cell[][] board, Player player) {
        List<Point> points = GameUtils.getEmptyPointsFromBoard(board);
        List<PointWithResult> pointWithResults = new ArrayList<>();
        for (Point point : points) {
            pointWithResults.add(getPointWithResult(board, player, point, points.size()));
        }
        return pointWithResults;
    }

    private static PointWithResult getPointWithResult(Cell[][] board, Player player, Point point, int emptyPointsSize) {
        int moveScores = getScoresSumForMove(board, player, point.getRow(), point.getColumn(), emptyPointsSize);
        board[point.getRow()][point.getColumn()].setPlayerMark(null);
        return new PointWithResult(point, moveScores);
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
}
