package com.example.trainingstudio.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.trainingstudio.R;

public class MainActivity extends AppCompatActivity implements ExampleFragment.MsgSend {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fragment start
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new ExampleFragment()).commit();


    }

    @Override
    public void Send(String email, String pass) {

        Fragement2 fragement2 = new Fragement2();
        POJO pojo = new POJO();
        pojo.setName(email);
        pojo.setPass(pass);
        Bundle bundle = new Bundle();
        bundle.putParcelable("key", pojo);

        fragement2.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, fragement2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}
