package com.timkhakimov.navigator.support

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.timkhakimov.navigator.NavFragmentSwitchHandler
import com.timkhakimov.navigator.NavFragmentFactory

/**
 * Created by Timur Khakimov on 05.08.2019.
 */
class SupportNavFragmentSwitchHandler<E : Enum<*>, F : SupportNavFragment<E>>(fragmentManager: FragmentManager,
                                                                              containerId: Int,
                                                                              navFragmentFactory: NavFragmentFactory<E, F>)
    : NavFragmentSwitchHandler<E, F, FragmentManager>(fragmentManager, containerId, navFragmentFactory) {

    override fun back(bundle: Bundle?) {
        if (fragmentManager.backStackEntryCount == 0) {
            return
        }
        fragmentManager.popBackStack()
        if (fragmentManager.backStackEntryCount == 0 || bundle == null) {
            return
        }
        val currentFragment = fragmentManager.findFragmentByTag(getCurrentFragmentTag())
        currentFragment?.arguments = bundle
    }

    override fun backTo(fragmentState: E, bundle: Bundle?) {
        while (fragmentManager.backStackEntryCount > 0 && getCurrentFragmentTag() != fragmentState.name) {
            fragmentManager.popBackStack()
        }
        if (fragmentManager.backStackEntryCount == 0) {
            forward(fragmentState, bundle)
        } else if (bundle != null) {
            val currentFragment = fragmentManager.findFragmentByTag(getCurrentFragmentTag())
            currentFragment?.arguments = bundle
        }
    }

    override fun replace(fragmentState: E, bundle: Bundle?) {
        back(null)
        forward(fragmentState, bundle)
    }

    override fun forward(fragmentState: E, bundle: Bundle?) {
        val transaction = fragmentManager.beginTransaction()
        val fragment = getNewInstanceFragment(fragmentState)
        fragment.arguments = bundle
        if (fragmentManager.backStackEntryCount == 0) {
            transaction.add(containerId, fragment, fragmentState.name)
        } else {
            transaction.replace(containerId, fragment, fragmentState.name)
        }
        transaction.addToBackStack(fragmentState.name)
        transaction.commit()
    }

    override fun root(fragmentState: E, bundle: Bundle?) {
        while (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
        forward(fragmentState, bundle)
    }

    override fun getCurrentFragmentTag(): String? {
        if (fragmentManager.backStackEntryCount == 0) {
            return null
        }
        return fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
    }
}