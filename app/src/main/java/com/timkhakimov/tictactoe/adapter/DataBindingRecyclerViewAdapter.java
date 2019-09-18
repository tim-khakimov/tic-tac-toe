package com.timkhakimov.tictactoe.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timur Khakimov on 18.09.2019
 * base RecyclerView adapter for using with data binding
 */
public abstract class DataBindingRecyclerViewAdapter<M> extends RecyclerView.Adapter<DataBindingRecyclerViewHolder> {

    protected List<M> items;

    public DataBindingRecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public DataBindingRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(viewType), parent, false);
        return new DataBindingRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingRecyclerViewHolder holder, int position) {
        M item = getItem(position);
        holder.bind(getVariableId(getItemViewType(position)), item);
    }

    @LayoutRes
    protected abstract int getLayoutRes(int viewType);

    /**
     * @param viewType
     * @return - the BR id of the variable to be set. For example, if the variable is <code>x</code>, then variableId will be <code>BR.x</code>.
     */
    protected abstract int getVariableId(int viewType);

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    public void setItems(List<M> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<M> itemList){
        items.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addItems(int position, List<M> itemList){
        items.addAll(position, itemList);
        notifyDataSetChanged();
    }

    public void addItem(M item){
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItem(int position, M item){
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll(){
        items.clear();
        notifyDataSetChanged();
    }

    public M getItem(int position) {
        return items.get(position);
    }

    public List<M> getItems(){
        return items;
    }
}
