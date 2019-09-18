package com.timkhakimov.tictactoe.fragments

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.timkhakimov.tictactoe.R
import com.timkhakimov.tictactoe.adapter.GameCellsAdapter
import com.timkhakimov.tictactoe.databinding.FragmentGameBinding
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.game.Game
import com.timkhakimov.tictactoe.game.observer.PointObserver
import com.timkhakimov.tictactoe.model.GameCell

/**
 * Created by Timur Khakimov on 18.09.2019
 * current game
 */
class GameFragment : BaseFragment<FragmentGameBinding>() {

    private val adapter : GameCellsAdapter
    private val pointObserver : PointObserver

    init {
        adapter = GameCellsAdapter()
        pointObserver = PointObserver { row, column ->
            updateGameState()
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_game
    }

    override fun getFragmentType(): FragmentType {
        return FragmentType.GAME
    }

    override fun onViewCreated(root: View) {

    }

    private fun setGameCells(game : Game) {
        binding.rvSpaces.adapter = adapter
        binding.rvSpaces.layoutManager = GridLayoutManager(requireContext(), game.board.size)
        adapter.items = createGameCells(game)
    }

    private fun updateGameState() {
        //todo update current player title and result status
    }

    private fun createGameCells(game: Game) : List<GameCell> {
        val gameCells = mutableListOf<GameCell>()
        for (row in 0 until game.board.size) {
            for (column in 0 until game.board[0].size) {
                gameCells.add(GameCell(game, row, column))
            }
        }
        return gameCells
    }

    override fun onDestroy() {
        adapter.removeCellObserver()
        //todo remove this.pointObserver from game
        super.onDestroy()
    }
}