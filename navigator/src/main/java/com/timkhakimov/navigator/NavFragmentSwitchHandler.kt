package com.timkhakimov.navigator

import android.os.Bundle
import com.timkhakimov.navigator.base.StateSwitchHandler

/**
 * Created by Timur Khakimov on 05.08.2019.
 */
abstract class NavFragmentSwitchHandler<E : Enum<*>, F : NavFragment<E>, FM>(var fragmentManager : FM,
                                                                                                       var containerId : Int,
                                                                                                       var navFragmentFactory : NavFragmentFactory<E, F>) : StateSwitchHandler<E, Bundle> {
    protected fun getNewInstanceFragment(state : E) : F {
        return navFragmentFactory.newInstance(state)
    }

    abstract fun getCurrentFragmentTag() : String?
}