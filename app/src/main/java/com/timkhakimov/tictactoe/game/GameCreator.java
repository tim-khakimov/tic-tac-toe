package com.timkhakimov.tictactoe.game;

import com.timkhakimov.tictactoe.game.model.Player;
import com.timkhakimov.tictactoe.game.opponent.Opponent;
import com.timkhakimov.tictactoe.game.opponent.computer.RandomMoveComputerOpponent;

/**
 * Created by Timur Khakimov on 18.09.2019
 * game creator
 */
public class GameCreator {

    private int boardSize;
    private boolean withComputerOpponent;
    private int computerOpponentType;
    private Player opponentPlayer;

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setComputerOpponentType(int computerOpponentType) {
        this.computerOpponentType = computerOpponentType;
        withComputerOpponent = true;
    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public Game create() {
        Game game = new Game(GameUtils.createBoard(boardSize));
        if (withComputerOpponent) {
            game.setOpponentPlayer(opponentPlayer);
            Opponent opponent = createComputerOpponent(computerOpponentType, game, opponentPlayer);
            opponent.update(0, 0);
        }
        clear();
        return game;
    }

    private void clear() {
        boardSize = 0;
        withComputerOpponent = false;
        computerOpponentType = 0;
        opponentPlayer = null;
    }

    private Opponent createComputerOpponent(int computerOpponentType, Game game, Player opponentPlayer) {
        return new RandomMoveComputerOpponent(game, opponentPlayer);
    }
}
