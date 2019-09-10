package com.timkhakimov.tictactoe

import android.os.Bundle
import com.timkhakimov.navigator.NavFragmentFactory
import com.timkhakimov.navigator.support.SupportNavControllerActivity
import com.timkhakimov.tictactoe.fragments.BaseFragment
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.fragments.navigation.FragmentsFactory

class MainActivity : SupportNavControllerActivity<FragmentType, BaseFragment<*>>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createFragmentsFactory(): NavFragmentFactory<FragmentType, BaseFragment<*>> {
        return FragmentsFactory()
    }

    override fun getContainerId(): Int {
        return R.id.flFragmentContainer
    }
}
