package com.example.trainingstudio.roomsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainingstudio.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText updateuserid, updateusername, updateuseremail;
    private Button update_btn;
    String username = "", useremail = "";
    int userid = 0;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        updateuserid = view.findViewById(R.id.updateuserid);
        updateusername = view.findViewById(R.id.updateusername);
        updateuseremail = view.findViewById(R.id.updateuseremail);
        update_btn = view.findViewById(R.id.update_btn);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userid = Integer.parseInt(updateuserid.getText().toString());
                username = updateusername.getText().toString();
                useremail = updateuseremail.getText().toString();

                List<SampleAddData> list = RoomExample.sampleDb.sampleDao().getData();
                int c = 0;
                for (SampleAddData sampleAddDatas : list) {
                    int i = sampleAddDatas.getId();
                    if (i == userid) {
                        c = 1;
                    }
                }
                if (c == 1) {
                    SampleAddData sampleAddData = new SampleAddData();
                    sampleAddData.setId(userid);
                    sampleAddData.setName(username);
                    sampleAddData.setEmail(useremail);
                    RoomExample.sampleDb.sampleDao().update(sampleAddData);
                    Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getActivity(), "change your id number", Toast.LENGTH_SHORT).show();

                }


                updateuserid.setText("");
                updateusername.setText("");
                updateuseremail.setText("");

            }
        });


        return view;
    }

}
