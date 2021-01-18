package com.example.trainingstudio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingstudio.R;

public class Fragement2 extends Fragment {
    TextView textView, textView2;
    POJO pojo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.examplefragment2, container, false);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);

        Bundle bundle = getArguments();

        if (bundle != null) {
            pojo = bundle.getParcelable("key");

        }


        if (pojo != null) {
            textView.setText(pojo.getName());
            textView2.setText(pojo.getPass());
        }
        return view;
    }
}
