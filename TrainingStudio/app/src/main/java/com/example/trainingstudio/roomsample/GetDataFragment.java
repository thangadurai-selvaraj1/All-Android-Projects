package com.example.trainingstudio.roomsample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingstudio.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetDataFragment extends Fragment {

    private TextView readData;

    public GetDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_data, container, false);
        readData = view.findViewById(R.id.readData);

        List<SampleAddData> list=RoomExample.sampleDb.sampleDao().getData();

        String info="";
        for (SampleAddData sampleAddData:list){
            int i=sampleAddData.getId();
            String name=sampleAddData.getName();
            String email=sampleAddData.getEmail();

            info=info+"\n\n"+"ID : "+i+"\n NAME : "+name+"\n EMAIL : "+email;

        }
        readData.setText(info);

        return view;
    }

}
