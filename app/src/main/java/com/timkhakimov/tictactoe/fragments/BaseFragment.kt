package com.timkhakimov.tictactoe.fragments

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timkhakimov.navigator.support.SupportNavFragment
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType

/**
 * Created by Timur Khakimov on 10.09.2019
 * parent class for all fragments
 */
abstract class BaseFragment<B : ViewDataBinding> : SupportNavFragment<FragmentType>() {

    protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}