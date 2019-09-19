package com.timkhakimov.tictactoe.helpers;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.timkhakimov.tictactoe.R;
import com.timkhakimov.tictactoe.game.model.Player;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Timur Khakimov on 15.09.2019
 * static methods for data binding
 */
public class DataBindingAdapters {

    @BindingAdapter({"playerMark"})
    public static void setPlayerMark(TextView textView, @Nullable Player side) {
        if (side == null) {
            textView.setText(null);
            return;
        }
        textView.setText(getPlayerTitleResId(side));
    }

    @BindingAdapter({"isFinished", "winner"})
    public static void setGameResult(TextView textView, boolean isFinished, @Nullable Player winner) {
        if (!isFinished) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(textView.getResources().getString(R.string.game_over));
        stringBuilder.append(" ");
        if(winner == null) {
            stringBuilder.append(textView.getResources().getString(R.string.draw));
        } else {
            stringBuilder.append(textView.getResources().getString(R.string.winner));
            stringBuilder.append(" ");
            stringBuilder.append(textView.getResources().getString(getPlayerTitleResId(winner)));
        }
        textView.setText(stringBuilder.toString());
    }

    @StringRes
    private static int getPlayerTitleResId(@NonNull Player player) {
        return player == Player.CROSS ? R.string.cross : R.string.nought;
    }
}
