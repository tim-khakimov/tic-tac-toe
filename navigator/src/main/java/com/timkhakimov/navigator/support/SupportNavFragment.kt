package com.timkhakimov.navigator.support

import android.os.Bundle
import android.support.v4.app.Fragment
import com.timkhakimov.navigator.NavFragment
import com.timkhakimov.navigator.base.Queue
import com.timkhakimov.navigator.base.StateNavigator
import com.timkhakimov.navigator.base.SwitchStateOperation

/**
 * Created by Timur Khakimov on 05.08.2019.
 */
abstract class SupportNavFragment<E> : Fragment(), NavFragment<E> {

    protected var navigator: StateNavigator<E, Bundle>? = null

    override fun setFragmentsSwitchCommandsFromQueue(switchStateCommandsQueue: Queue<SwitchStateOperation<E, Bundle>>) {
        if(navigator == null) {
            return
        }
        var queue = switchStateCommandsQueue.removeAll()
        while (!queue.isEmpty()) {
            navigator!!.addSwitchStateOperation(queue.remove())
        }
        navigator!!.handleOperations()
    }

    override fun setFragmentsNavigator(fragmentsNavigator: StateNavigator<E, Bundle>?) {
        navigator = fragmentsNavigator
    }
}