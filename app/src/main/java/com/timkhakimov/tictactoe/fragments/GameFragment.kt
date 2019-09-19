package com.timkhakimov.tictactoe.fragments

import android.arch.lifecycle.Observer
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

    private val adapter = GameCellsAdapter()
    private val pointObserver: PointObserver

    init {
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
        mainViewModel.gameLiveData.observe(this, Observer { game ->
            if (game != null) {
                game.addObserver(pointObserver)
                setGameCells(game)
                updateGameState()
            }
        })
    }

    private fun setGameCells(game: Game) {
        binding.rvSpaces.adapter = adapter
        binding.rvSpaces.layoutManager = GridLayoutManager(requireContext(), game.board.size)
        adapter.items = createGameCells(game)
    }

    private fun updateGameState() {
        mainViewModel.gameLiveData.value?.let {
            binding.game = it
        }
    }

    private fun createGameCells(game: Game): List<GameCell> {
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
        mainViewModel.gameLiveData.value?.removeObserver(pointObserver)
        super.onDestroy()
    }
}