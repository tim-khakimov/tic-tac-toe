package com.timkhakimov.navigator.support

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.timkhakimov.navigator.NavFragmentFactory
import com.timkhakimov.navigator.NavFragmentsControllerBehavior
import com.timkhakimov.navigator.base.StateNavigator
import com.timkhakimov.navigator.base.SwitchStateNavigator

/**
 * Created by Timur Khakimov on 05.08.2019.
 */
abstract class SupportNavControllerActivity<E : Enum<*>, F : SupportNavFragment<E>> : AppCompatActivity(), NavFragmentsControllerBehavior<E, F> {

    protected lateinit var fragmentFactory: NavFragmentFactory<E, F>
    protected lateinit var fragmentsNavigator: SwitchStateNavigator<E, Bundle>

    override open fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigator()
    }

    protected open fun initNavigator() {
        fragmentFactory = createFragmentsFactory()
        val fragmentsSwitchHandler = SupportNavFragmentSwitchHandler(supportFragmentManager, getContainerId(), fragmentFactory)
        fragmentsNavigator = SwitchStateNavigator(fragmentsSwitchHandler)
        fragmentFactory.fragmentsNavigator = fragmentsNavigator
    }

    override fun getNavigator(): StateNavigator<E, Bundle> {
        return fragmentsNavigator
    }

    override fun getNavFragmentsFactory(): NavFragmentFactory<E, F> {
        return fragmentFactory
    }

    abstract fun createFragmentsFactory() : NavFragmentFactory<E, F>
}