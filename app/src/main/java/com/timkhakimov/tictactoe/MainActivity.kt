package com.timkhakimov.tictactoe

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.timkhakimov.navigator.NavFragmentFactory
import com.timkhakimov.navigator.support.SupportNavControllerActivity
import com.timkhakimov.tictactoe.fragments.BaseFragment
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.fragments.navigation.FragmentsFactory
import com.timkhakimov.tictactoe.viewmodel.MainViewModel

class MainActivity : SupportNavControllerActivity<FragmentType, BaseFragment<*>>() {

    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
    }

    override fun createFragmentsFactory(): NavFragmentFactory<FragmentType, BaseFragment<*>> {
        return FragmentsFactory()
    }

    override fun getContainerId(): Int {
        return R.id.flFragmentContainer
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
