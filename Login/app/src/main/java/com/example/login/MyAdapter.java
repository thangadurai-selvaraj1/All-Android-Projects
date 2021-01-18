package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Model> models;
    OnClickListener onClickListener;
    Context context;

    public MyAdapter(ArrayList<Model> models,Context context, OnClickListener onClickListener) {
        this.models = models;
        this.onClickListener = onClickListener;
        this.context=context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Insert;
        public Button btn_Delete;
        public Button btn_Edit;
        public LinearLayout linearLayout;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_Insert = (TextView) itemView.findViewById(R.id.contact_name);
            btn_Delete = (Button) itemView.findViewById(R.id.delete_button);
            btn_Edit = (Button) itemView.findViewById(R.id.edit_button);
            linearLayout=itemView.findViewById(R.id.animation_linear);
            btn_Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    models.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

  btn_Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    String name = tv_Insert.getText().toString();
                    onClickListener.onItem(index, models.get(getAdapterPosition()), name);


                }
            });

        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.display_text, viewGroup, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        Model model = models.get(i);
        viewHolder.tv_Insert.setText(model.getName());
        viewHolder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.animation));
        viewHolder.tv_Insert.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_text_button));
        viewHolder.btn_Edit.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_text_button));
        viewHolder.btn_Delete.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_text_button));


    }


    @Override
    public int getItemCount() {
        return models.size();
    }


}
