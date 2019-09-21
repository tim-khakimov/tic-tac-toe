package com.timkhakimov.tictactoe.fragments

import android.view.View
import com.timkhakimov.tictactoe.Constants
import com.timkhakimov.tictactoe.R
import com.timkhakimov.tictactoe.databinding.FragmentStartBinding
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType

/**
 * Created by Timur Khakimov on 19.09.2019
 * start fragment for choosing game mode
 */
class StartFragment : BaseFragment<FragmentStartBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_start
    }

    override fun getFragmentType(): FragmentType {
        return FragmentType.START
    }

    override fun onViewCreated(root: View) {
        binding.tvTwoPlayers.setOnClickListener {
            mainViewModel.setBoardSize(Constants.Board.SIZE_3)
            mainViewModel.startGame()
            switchFragment(FragmentType.GAME)
        }
        binding.tvComputerOpponentRandomMove.setOnClickListener {
            setComputerOpponent(Constants.Opponents.RANDOM_MOVE)
        }
        binding.tvComputerOpponentFindBestMoveMinimax.setOnClickListener {
            setComputerOpponent(Constants.Opponents.FIND_BEST_MOVE_USING_MINIMAX)
        }
    }

    private fun setComputerOpponent(computerOpponentType: Int) {
        mainViewModel.setBoardSize(Constants.Board.SIZE_3)
        mainViewModel.setComputerOpponentType(computerOpponentType)
        switchFragment(FragmentType.CHOOSE_YOUR_SIDE)
    }
}