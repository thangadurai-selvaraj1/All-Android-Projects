package com.example.barcode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Model> models;

    Context context;

    public MyAdapter(ArrayList<Model> models, Context context) {
        this.models = models;

        this.context=context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Insert;
        public Button btn_Delete;

        public LinearLayout linearLayout;


        public ViewHolder(final View itemView) {
            super(itemView);
           tv_Insert=itemView.findViewById(R.id.contact_name);
            btn_Delete = (Button) itemView.findViewById(R.id.delete_button);
            linearLayout=itemView.findViewById(R.id.animation_linear);
            btn_Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    models.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });



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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        Model model = models.get(i);
        viewHolder.tv_Insert.setText(model.getName());
        viewHolder.btn_Delete.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_text_button));
        viewHolder.tv_Insert.setAnimation(AnimationUtils.loadAnimation(context,R.anim.animation));

    }


    @Override
    public int getItemCount() {
        return models.size();
    }


}
