package com.timkhakimov.tictactoe.helpers;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.timkhakimov.tictactoe.R;
import com.timkhakimov.tictactoe.game.model.Player;

/**
 * Created by Timur Khakimov on 15.09.2019
 * static methods for data binding
 */
public class DataBindingAdapters {

    @BindingAdapter({"playerMark"})
    public static void setPlayerMark(TextView textView, Player side) {
        if (side == null) {
            textView.setText(null);
            return;
        }
        textView.setText(side == Player.CROSS ? R.string.cross : R.string.nought);
    }
}
