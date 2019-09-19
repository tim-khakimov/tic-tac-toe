package com.timkhakimov.tictactoe.fragments

import android.view.View
import android.widget.Toast
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
            Toast.makeText(requireContext(), "START", Toast.LENGTH_SHORT).show()
            mainViewModel.setBoardSize(Constants.Board.SIZE_3)
            mainViewModel.startGame()
        }
    }
}