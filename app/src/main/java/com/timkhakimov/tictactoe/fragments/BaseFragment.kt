package com.timkhakimov.tictactoe.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.timkhakimov.navigator.base.SwitchStateDirection
import com.timkhakimov.navigator.support.SupportNavFragment
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType
import com.timkhakimov.tictactoe.fragments.navigation.FragmentsSwitcher
import com.timkhakimov.tictactoe.viewmodel.BaseViewModel
import com.timkhakimov.tictactoe.viewmodel.MainViewModel

/**
 * Created by Timur Khakimov on 10.09.2019
 * parent class for all fragments
 */
abstract class BaseFragment<B : ViewDataBinding> : SupportNavFragment<FragmentType>(), FragmentsSwitcher {

    protected lateinit var mainViewModel: MainViewModel
    protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = getViewModel(MainViewModel::class.java)
        onFragmentCreated(savedInstanceState)
    }

    protected open fun onFragmentCreated(savedInstanceState: Bundle?) {
        //override if necessary.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        val root = binding.root
        onViewCreated(root)
        return root
    }

    @LayoutRes
    protected abstract fun getLayoutRes() : Int

    protected abstract fun getFragmentType() : FragmentType

    protected abstract fun onViewCreated(root: View)

    protected fun <T : BaseViewModel> getViewModel(viewModelClass: Class<T>): T {
        val viewModelProvider = ViewModelProvider(activity!!, ViewModelProvider.AndroidViewModelFactory(activity!!.application))
        val viewModel = viewModelProvider.get(viewModelClass)
        viewModel.switchFragmentOperationsQueueLiveData.observe(this, Observer { switchStateCommands ->
            System.out.println("BaseFragment : switchFragmentOperationsQueueLiveData.observe")
            switchStateCommands?.let { setFragmentsSwitchCommandsFromQueue(it) }
        })
        return viewModel
    }

    override fun back() {
        mainViewModel.back()
    }

    override fun switchFragment(fragmentType: FragmentType) {
        mainViewModel.switchFragment(fragmentType)
    }

    override fun switchFragment(fragmentType: FragmentType, direction: SwitchStateDirection, bundle: Bundle?) {
        mainViewModel.switchFragment(fragmentType, direction, bundle)
    }

    protected fun toast(text : String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}