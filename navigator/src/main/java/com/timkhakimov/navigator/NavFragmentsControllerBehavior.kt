package com.timkhakimov.navigator

import android.os.Bundle
import com.timkhakimov.navigator.base.StateNavigator

/**
 * Created by Timur Khakimov on 20.08.2019
 * use in fragments and activities for creating and showing fragments
 */
interface NavFragmentsControllerBehavior<E, F : NavFragment<E>> {
    fun getContainerId() : Int
    fun getNavFragmentsFactory() : NavFragmentFactory<E, F>
    fun getNavigator() : StateNavigator<E, Bundle>
}