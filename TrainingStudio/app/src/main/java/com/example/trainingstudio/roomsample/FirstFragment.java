package com.example.trainingstudio.roomsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trainingstudio.R;


public class FirstFragment extends Fragment {

    private Button adduser,viewuser,deleteuser,updateuser;


    public FirstFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

        adduser=view.findViewById(R.id.adduser);
        viewuser=view.findViewById(R.id.viewuser);
        deleteuser=view.findViewById(R.id.deleteuser);
        updateuser=view.findViewById(R.id.updateuser);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomExample.fragmentManager.beginTransaction().replace(R.id.RoomExample,new SecondFragment()).addToBackStack(null).commit();
            }
        });

        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomExample.fragmentManager.beginTransaction().replace(R.id.RoomExample,new GetDataFragment()).addToBackStack(null).commit();
            }
        });

        deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomExample.fragmentManager.beginTransaction().replace(R.id.RoomExample,new DeleteDataFragment()).addToBackStack(null).commit();

            }
        });

        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomExample.fragmentManager.beginTransaction().replace(R.id.RoomExample,new UpdateFragment()).addToBackStack(null).commit();

            }
        });

        return view;
    }

}
