package com.timkhakimov.tictactoe.fragments.navigation

import android.os.Bundle
import com.timkhakimov.navigator.base.SwitchStateDirection

/**
 * Created by Timur Khakimov on 18.09.2019
 * interface using switch fragments
 */
interface FragmentsSwitcher {
    fun back()
    fun switchFragment(fragmentType: FragmentType)
    fun switchFragment(fragmentType: FragmentType, direction: SwitchStateDirection, bundle: Bundle?)
}