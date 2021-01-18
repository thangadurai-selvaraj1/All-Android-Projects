package com.example.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private Button btn_Signup, btn_Login;
    private EditText inputName, inputEmail, inputPhone, inputPassword, inputConfirmPassword;
    private TextInputLayout textInputLayoutName, textInputLayoutEmail, textInputLayoutPhone, textInputLayoutPassword, textInputLayoutConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputLayoutName = findViewById(R.id.user_Name);
        textInputLayoutEmail = findViewById(R.id.user_Email);
        textInputLayoutPhone = findViewById(R.id.user_Phone);
        textInputLayoutPassword = findViewById(R.id.user_Password);
        textInputLayoutConfirmPassword = findViewById(R.id.user_Confirm_Password);


        inputName = findViewById(R.id.input_Name);
        inputEmail = findViewById(R.id.input_Email);
        inputPhone = findViewById(R.id.input_Phone);
        inputPassword = findViewById(R.id.input_Password);
        inputConfirmPassword = findViewById(R.id.input_Confirm_Password);


        btn_Signup = findViewById(R.id.btn_SignUp);
        btn_Login = findViewById(R.id.btn_Login);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidate();

            }
        });


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,ListView.class);
                startActivity(intent);
            }
        });
    }




    private void checkValidate() {

        if (!validName() | !validEmail() | !validPhone() | !validPassword() | !validConfirmPassword()) {
            return;
        }

    }

    private boolean validName() {
        String name = inputName.getText().toString().trim();
        if (name.isEmpty()) {
            textInputLayoutName.setError("Enter Name");
            return false;
        } else {

            textInputLayoutName.setError(null);

            return true;

        }

    }

    private boolean validEmail() {
        String name = inputEmail.getText().toString().trim();
        if (name.isEmpty() || name.endsWith(".com")) {
            textInputLayoutEmail.setError("Enter Email");
            return false;
        } else {

            textInputLayoutEmail.setError(null);

            return true;

        }

    }

    private boolean validPhone() {
        String name = inputPhone.getText().toString().trim();
        int length = name.length();

        if (name.isEmpty()) {
            textInputLayoutPhone.setError("Enter Phone");
            inputPhone.setTextColor(getResources().getColor(R.color.white));
            return false;
        } else if (length > 10 || length < 10) {
            textInputLayoutPhone.setError("Enter valid Phone");
            inputPhone.setTextColor(getResources().getColor(R.color.white));
            return false;
        } else {

            textInputLayoutPhone.setError(null);
            inputPhone.setTextColor(getResources().getColor(R.color.red));
            return true;

        }

    }

    private boolean validPassword() {
        String password = inputPassword.getText().toString().trim();
        int length = password.length();
        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Enter Password");
            return false;
        } else if (length < 8) {
            textInputLayoutPassword.setError("Enter 8 Length Password");
            return false;
        } else {
            textInputLayoutPassword.setError(null);

            return true;
        }

    }

    private boolean validConfirmPassword() {
        String password = inputConfirmPassword.getText().toString().trim();
        int length = password.length();
        if (password.isEmpty()) {
            textInputLayoutConfirmPassword.setError("Enter Confirm Password");
            return false;
        } else if (length < 8) {
            textInputLayoutConfirmPassword.setError("Enter 8 Length Password");
            return false;
        } else {
            textInputLayoutConfirmPassword.setError(null);

            return true;
        }

    }

}
