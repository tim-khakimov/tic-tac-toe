package com.timkhakimov.tictactoe.fragments

import android.view.View
import com.timkhakimov.navigator.base.SwitchStateDirection
import com.timkhakimov.tictactoe.R
import com.timkhakimov.tictactoe.databinding.FragmentChooseYourSideBinding
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.game.model.Player

/**
 * Created by Timur Khakimov on 21.09.2019
 * choosing player's side
 */
class ChooseYourSideFragment : BaseFragment<FragmentChooseYourSideBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_choose_your_side
    }

    override fun getFragmentType(): FragmentType {
        return FragmentType.CHOOSE_YOUR_SIDE
    }

    override fun onViewCreated(root: View) {
        binding.tvCrosses.setOnClickListener { setMyPlayer(Player.CROSS) }
        binding.tvNoughts.setOnClickListener { setMyPlayer(Player.NOUGHT) }
        binding.tvRandom.setOnClickListener { setMyPlayer(getRandomPlayer()) }
    }

    private fun getRandomPlayer(): Player {
        var randomPlayer = if (Math.random() >= 0.5) Player.CROSS else Player.NOUGHT
        toast(String.format("Player : %s", randomPlayer.name))
        return randomPlayer
    }

    private fun setMyPlayer(myPlayer: Player) {
        mainViewModel.setOpponentPlayer(Player.getOther(myPlayer))
        mainViewModel.startGame()
        switchFragment(FragmentType.GAME, SwitchStateDirection.REPLACE, null)
    }
}