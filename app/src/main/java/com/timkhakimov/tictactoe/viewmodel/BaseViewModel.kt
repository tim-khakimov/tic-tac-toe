package com.timkhakimov.tictactoe.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.timkhakimov.navigator.base.Queue
import com.timkhakimov.navigator.base.SwitchStateDirection
import com.timkhakimov.navigator.base.SwitchStateOperation
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.fragments.navigation.FragmentsSwitcher

/**
 * Created by Timur Khakimov on 18.09.2019
 * base viewmodel
 */
abstract class BaseViewModel : ViewModel(), FragmentsSwitcher {

    val switchFragmentOperationsQueueLiveData = MutableLiveData<Queue<SwitchStateOperation<FragmentType, Bundle>>>()

    init {
        switchFragmentOperationsQueueLiveData.value = Queue()
    }

    override fun back() {
        addSwitchFragmentOperation(SwitchStateOperation(SwitchStateDirection.BACK, null, null))
    }

    override fun switchFragment(fragmentType: FragmentType) {
        switchFragment(fragmentType, SwitchStateDirection.FORWARD, null)
    }

    override fun switchFragment(fragmentType: FragmentType, direction: SwitchStateDirection, bundle: Bundle?) {
        addSwitchFragmentOperation(SwitchStateOperation(direction, fragmentType, bundle))
    }

    private fun addSwitchFragmentOperation(command: SwitchStateOperation<FragmentType, Bundle>) {
        System.out.println("BaseViewModel : addSwitchFragmentOperation() command.state = " + command.state)
        val queue = switchFragmentOperationsQueueLiveData.value
        queue!!.add(command)
        switchFragmentOperationsQueueLiveData.value = queue
    }
}