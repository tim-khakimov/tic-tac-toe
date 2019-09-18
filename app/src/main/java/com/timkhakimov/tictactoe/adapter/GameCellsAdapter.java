package com.timkhakimov.tictactoe.adapter;

import android.support.annotation.NonNull;

import com.timkhakimov.tictactoe.R;
import com.timkhakimov.tictactoe.model.GameCell;

/**
 * Created by Timur Khakimov on 18.09.2019
 * recyclerview adapter for showing game board cells
 */
public class GameCellsAdapter extends DataBindingRecyclerViewAdapter<GameCell> {

    @Override
    public void onBindViewHolder(@NonNull DataBindingRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.view_cell;
    }

    @Override
    protected int getVariableId(int viewType) {
        return com.timkhakimov.tictactoe.BR.gameCell;
    }

    public void removeCellObserver() {
        for (GameCell cell : items) {
            cell.removeCellObserver();
        }
    }
}