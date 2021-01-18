package com.example.trainingstudio.db;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingstudio.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<ThemesDetail> models;
    private int oldPosition;
    Context context;
    Communication communication;


    public interface Communication {

        void success(int oldPosition, int newPos);
    }

    public Adapter(List<ThemesDetail> models, Context context, Communication communication) {

        this.models = models;
        this.context = context;
        this.communication = communication;

    }

    public void setOnClickListener(Communication clickListener) {
        communication = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Insert;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv_Insert = itemView.findViewById(R.id.contact_name);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.display_element, viewGroup, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final ThemesDetail model = getItem(position);
        viewHolder.tv_Insert.setText(model.getTextTitleColor() + " , " + model.getEditTextColor());
        viewHolder.tv_Insert.setBackgroundColor(Color.parseColor(model.getEditTextColor()));

        viewHolder.tv_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communication.success(oldPosition, viewHolder.getAdapterPosition());
                oldPosition = viewHolder.getAdapterPosition();
            }
        });

    }

    private ThemesDetail getItem(int position) {
        return models.get(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
