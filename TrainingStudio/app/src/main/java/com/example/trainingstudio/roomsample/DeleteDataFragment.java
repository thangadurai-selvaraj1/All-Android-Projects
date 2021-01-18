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
public class DeleteDataFragment extends Fragment {

    EditText delete_id;
    Button delete;

    public DeleteDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_delete_data, container, false);

         delete_id=view.findViewById(R.id.deleteid);
         delete=view.findViewById(R.id.delete_btn);

         delete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int id=Integer.parseInt(delete_id.getText().toString());


                 List<SampleAddData> list= RoomExample.sampleDb.sampleDao().getData();
                 int c=0;
                 for (SampleAddData sampleAddDatas:list) {
                     int i = sampleAddDatas.getId();
                     if (i==id) {
                         c=1;
                     }
                 }

                 if (c==1){
                     SampleAddData sampleAddData=new SampleAddData();
                     sampleAddData.setId(id);

                     RoomExample.sampleDb.sampleDao().delete(sampleAddData);
                     Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();

                 }
                 else {
                     Toast.makeText(getActivity(), "No such data in db", Toast.LENGTH_SHORT).show();

                 }


                 delete_id.setText("");
             }
         });

        return view;
    }

}
