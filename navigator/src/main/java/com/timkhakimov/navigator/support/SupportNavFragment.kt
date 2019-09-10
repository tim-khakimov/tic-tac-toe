package com.timkhakimov.navigator.support

import android.os.Bundle
import android.support.v4.app.Fragment
import com.timkhakimov.navigator.NavFragment
import com.timkhakimov.navigator.base.StateNavigator
import com.timkhakimov.navigator.base.SwitchStateOperation
import java.util.*

/**
 * Created by Timur Khakimov on 05.08.2019.
 */
abstract class SupportNavFragment<E> : Fragment(), NavFragment<E> {

    protected var navigator: StateNavigator<E, Bundle>? = null

    override fun setFragmentsSwitchCommandsFromQueue(switchStateCommandsDeque: Deque<SwitchStateOperation<E, Bundle>>) {
        if(navigator == null) {
            return
        }
        while (!switchStateCommandsDeque.isEmpty()) {
            navigator!!.addSwitchStateOperation(switchStateCommandsDeque.removeFirst())
        }
        navigator!!.handleOperations()
    }

    override fun setFragmentsNavigator(fragmentsNavigator: StateNavigator<E, Bundle>?) {
        navigator = fragmentsNavigator
    }
}