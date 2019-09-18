package com.timkhakimov.tictactoe.fragments.navigation

import com.timkhakimov.navigator.NavFragmentFactory
import com.timkhakimov.tictactoe.fragments.BaseFragment
import com.timkhakimov.tictactoe.fragments.EmptyFragment
import com.timkhakimov.tictactoe.fragments.GameFragment

/**
 * Created by Timur Khakimov on 10.09.2019
 */
class FragmentsFactory : NavFragmentFactory<FragmentType, BaseFragment<*>>() {
    override fun getNewBaseFragment(type: FragmentType): BaseFragment<*> {
        return when (type) {
            FragmentType.GAME -> GameFragment()
            else -> EmptyFragment()
        }
    }
}