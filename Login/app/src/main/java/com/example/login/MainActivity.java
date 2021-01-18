package com.example.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        POJO pojo = new POJO();

        activityMainBinding.setContact(pojo);

        activityMainBinding.setListener(this);
/*
        SimpleClickEvent simpleClickEvent = new SimpleClickEvent(activityMainBinding.next.inputName, activityMainBinding.next.inputPassword);

        activityMainBinding.next.setClickbutton(simpleClickEvent);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Login:

                EditText name = activityMainBinding.next.inputName;
                EditText last = activityMainBinding.next.inputPassword;

                String nameFirstCheck = name.getText().toString();
                name.setTextColor(getResources().getColor(R.color.white));
                String nameLastCheck = last.getText().toString();
                last.setTextColor(getResources().getColor(R.color.white));

                if(nameFirstCheck.isEmpty() || nameLastCheck.isEmpty()) {

                    activityMainBinding.next.userName.setError("Enter First Name");
                    activityMainBinding.next.userPassword.setError("Enter Last Name");

                }else {

               /* name.setText("");
                last.setText("");*/


                    activityMainBinding.next.userName.setError(null);
                    activityMainBinding.next.userPassword.setError(null);
                    name.setTextColor(getResources().getColor(R.color.red));
                    last.setTextColor(getResources().getColor(R.color.red));


                }
                break;
        }
    }

    public class SimpleClickEvent {

        public EditText name, last;

        public SimpleClickEvent(EditText name, EditText last) {
            this.name = name;
            this.last = last;
        }

        public void update(View view) {


        }


    }


 /*   private void checkValidate() {

        if (!validName() | !validPassword()) {
            return;
        }

    }

    private boolean validName() {
        String name = inputName.getText().toString().trim();
        if (name.isEmpty()) {
            textInputLayoutName.setError("enter name");
            inputName.setTextColor(getResources().getColor(R.color.white));
            //.setBackgroundColor(getResources().getColor(android.R.color.white));

            return false;
        } else {

            textInputLayoutName.setError(null);
            inputName.setTextColor(getResources().getColor(R.color.red));

            return true;

        }

    }

    private boolean validPassword() {
        String password = inputPassword.getText().toString().trim();
        if (password.isEmpty()) {
            textInputLayoutPassword.setError("enter password");
            inputPassword.setTextColor(getResources().getColor(R.color.white));
            return false;
        } else {
            textInputLayoutPassword.setError(null);
            inputPassword.setTextColor(getResources().getColor(R.color.red));
            return true;
        }

    }*/

    public void next_btn(View view) {

        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
}
