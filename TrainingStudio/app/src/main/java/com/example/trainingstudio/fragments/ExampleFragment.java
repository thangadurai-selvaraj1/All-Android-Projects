package com.example.trainingstudio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.trainingstudio.R;

public class ExampleFragment extends Fragment {

    MsgSend msgSend;
    EditText editText, editText2;
    Button button;
    POJO pojo;

    public interface MsgSend {
        void Send(String email,String pass);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.examplefragment1, container, false);
        editText = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = editText.getText().toString();
                String password = editText.getText().toString();


                if (!email.isEmpty()&&!password.isEmpty()) {
                    msgSend.Send(email, password);
                }

             /*   if (!email.equals("")&&!password.equals(""))
                if (msgSend != null) msgSend.Send(email, password);*/
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MsgSend) {
            msgSend = (MsgSend) context;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        editText.setText("");
        editText2.setText("");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        msgSend=null;
    }
}
