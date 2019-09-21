package com.timkhakimov.tictactoe.adapter.dividers;

/**
 * Created by Timur Khakimov on 21.09.2019
 */
public interface DividersOffsetsBehavior {
    int getLeftOffset(int position);
    int getTopOffset(int position);
    int getRightOffset(int position);
    int getBottomOffset(int position);
}
