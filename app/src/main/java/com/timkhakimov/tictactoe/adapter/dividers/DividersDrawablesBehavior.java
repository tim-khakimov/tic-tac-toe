package com.timkhakimov.tictactoe.adapter.dividers;

import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Timur Khakimov on 21.09.2019
 */
public interface DividersDrawablesBehavior {
    @Nullable
    Drawable getLeftDivider(int position);
    @Nullable
    Drawable getTopDivider(int position);
    @Nullable
    Drawable getRightDivider(int position);
    @Nullable
    Drawable getBottomDivider(int position);
}
