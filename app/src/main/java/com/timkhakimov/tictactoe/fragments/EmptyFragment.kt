package com.timkhakimov.tictactoe.fragments

import android.view.View
import com.timkhakimov.tictactoe.R
import com.timkhakimov.tictactoe.databinding.FragmentEmptyBinding
import com.timkhakimov.tictactoe.fragments.navigation.FragmentType

/**
 * Created by Timur Khakimov on 10.09.2019
 */
class EmptyFragment : BaseFragment<FragmentEmptyBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_empty
    }

    override fun getFragmentType(): FragmentType {
        return FragmentType.EMPTY
    }

    override fun onViewCreated(root: View) {
        //do nothing
    }
}