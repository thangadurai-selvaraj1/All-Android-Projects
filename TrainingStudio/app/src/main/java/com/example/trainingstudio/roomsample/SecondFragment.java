package com.example.trainingstudio.roomsample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainingstudio.R;

import java.util.List;


public class SecondFragment extends Fragment {

    private EditText id, name, email;
    private Button save;
    String username = "", useremail = "";
    int userid = 0;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        id = view.findViewById(R.id.editTextid);
        name = view.findViewById(R.id.editTextname);
        email = view.findViewById(R.id.editTextemail);
        save = view.findViewById(R.id.buttonsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userid = Integer.parseInt(id.getText().toString());
                username = name.getText().toString();
                useremail = email.getText().toString();


                List<SampleAddData> list = RoomExample.sampleDb.sampleDao().getData();
                int c = 0;
                for (SampleAddData sampleAddDatas : list) {
                    int i = sampleAddDatas.getId();
                    if (i == userid) {
                        c = 1;
                    }
                }
                if (c == 1) {
                    Toast.makeText(getActivity(), "change your id number ", Toast.LENGTH_SHORT).show();

                } else {
                    SampleAddData sampleAddData = new SampleAddData();
                    sampleAddData.setId(userid);
                    sampleAddData.setName(username);
                    sampleAddData.setEmail(useremail);
                    RoomExample.sampleDb.sampleDao().insert(sampleAddData);
                    Toast.makeText(getActivity(), "inserted", Toast.LENGTH_SHORT).show();

                }


                id.setText("");
                name.setText("");
                email.setText("");


            }
        });


        return view;
    }


}
