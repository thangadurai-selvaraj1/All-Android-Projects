package com.example.retrofit;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.databinding.DisplayBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Model> models;


    public MyAdapter(List<Model> models) {
        this.models = models;


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DisplayBinding displayBinding;

        public ViewHolder(@NonNull DisplayBinding itemView) {
            super(itemView.getRoot());
            displayBinding=itemView;

        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        DisplayBinding displayBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.display, viewGroup, false);
        return new ViewHolder(displayBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int position) {
        Model model = models.get(position);
        viewHolder.displayBinding.setModel(model);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
