package com.timkhakimov.tictactoe.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.game.Game
import com.timkhakimov.tictactoe.game.GameCreator
import com.timkhakimov.tictactoe.game.model.Player

/**
 * Created by Timur Khakimov on 18.09.2019
 * common viewmodel for using in all fragments and activity
 */
class MainViewModel : BaseViewModel() {

    val gameLiveData = MutableLiveData<Game>()
    private val gameCreator = GameCreator()

    fun setBoardSize(size: Int) {
        gameCreator.setBoardSize(size)
    }

    fun setComputerOpponentType(computerOpponentType: Int) {
        gameCreator.setComputerOpponentType(computerOpponentType)
    }

    fun setOpponentPlayer(opponentPlayer: Player) {
        gameCreator.setOpponentPlayer(opponentPlayer)
    }

    fun startGame() {
        gameLiveData.value = gameCreator.create()
        switchFragment(FragmentType.GAME)
    }
}