package com.timkhakimov.navigator

import android.os.Bundle
import com.timkhakimov.navigator.base.StateNavigator
import com.timkhakimov.navigator.base.SwitchStateOperation
import java.util.*

/**
 * Created by Timur Khakimov on 01.08.2019
 * Интерфейс - поведение для фрагментов, реализующих возможность переключения фрагментов с помощью StateNavigator
 */
interface NavFragment<E : Enum<*>> {
    fun setFragmentsSwitchCommandsFromQueue(switchStateCommandsDeque : Deque<SwitchStateOperation<E, Bundle>>)
    fun setFragmentsNavigator(fragmentsNavigator: StateNavigator<E, Bundle>?)
}
