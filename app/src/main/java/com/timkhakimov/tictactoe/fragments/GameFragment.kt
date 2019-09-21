package com.timkhakimov.tictactoe.fragments

import android.arch.lifecycle.Observer
import android.graphics.drawable.Drawable
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.timkhakimov.tictactoe.R
import com.timkhakimov.tictactoe.adapter.GameCellsAdapter
import com.timkhakimov.tictactoe.adapter.dividers.BaseDividersDecoration
import com.timkhakimov.tictactoe.adapter.dividers.DividersDrawablesBehavior
import com.timkhakimov.tictactoe.adapter.dividers.DividersOffsetsBehavior
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
    private lateinit var dividersDecoration : BaseDividersDecoration

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
                initDividersDecoration(game.board.size)
                setGameCells(game)
                updateGameState()
            }
        })
    }

    private fun initDividersDecoration(boardSize: Int) {
        val dividersDrawable = resources.getDrawable(R.drawable.shape_rectangle_black)
        val dividersWidth = resources.getDimensionPixelOffset(R.dimen.size_1)
        dividersDecoration = BaseDividersDecoration(getOffsetsBehavior(dividersWidth, boardSize), getDrawablesBehavior(dividersDrawable, boardSize))
        binding.rvSpaces.addItemDecoration(dividersDecoration)
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

    companion object {

        fun getOffsetsBehavior(dividerWidth : Int, boardSize : Int) : DividersOffsetsBehavior {
            return object : DividersOffsetsBehavior {

                override fun getLeftOffset(position: Int): Int {
                    return if(isFirstColumn(position, boardSize)) 0 else dividerWidth
                }

                override fun getTopOffset(position: Int): Int {
                    return if(isFirstRow(position, boardSize)) 0 else dividerWidth
                }

                override fun getRightOffset(position: Int): Int {
                    return 0
                }

                override fun getBottomOffset(position: Int): Int {
                    return 0
                }
            }
        }

        fun getDrawablesBehavior(dividerDrawable : Drawable, boardSize: Int) : DividersDrawablesBehavior {
            return object : DividersDrawablesBehavior {
                override fun getLeftDivider(position: Int): Drawable? {
                    return if(isFirstColumn(position, boardSize)) null else dividerDrawable
                }

                override fun getTopDivider(position: Int): Drawable? {
                    return if(isFirstRow(position, boardSize)) null else dividerDrawable
                }

                override fun getRightDivider(position: Int): Drawable? {
                    return null
                }

                override fun getBottomDivider(position: Int): Drawable? {
                    return null
                }
            }
        }

        private fun isFirstRow(position: Int, boardSize: Int): Boolean {
            return position < boardSize
        }

        private fun isFirstColumn(position: Int, boardSize: Int): Boolean {
            return position % boardSize == 0
        }
    }
}