package com.example.trainingstudio;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.trainingstudio.databinding.ActivityMain2Binding;
import com.example.trainingstudio.db.AppDatabase;
import com.example.trainingstudio.db.ThemesDetail;
import com.example.trainingstudio.interfaces.LoginInterface;
import com.example.trainingstudio.model.Login;
import com.example.trainingstudio.viewmodel.LoginViewModel;

public class MainActivity2 extends AppCompatActivity implements LoginInterface {

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db=AppDatabase.getInstance(this);

        ActivityMain2Binding activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        ThemesDetail themesDetail = db.themesDao().fetchTheme(true);
        Login login = new Login();
        LoginViewModel loginViewModel = new LoginViewModel(this, login);
        activityMain2Binding.setLoginViewModel(loginViewModel);
        activityMain2Binding.setThemes(themesDetail);
        activityMain2Binding.setLoginModel(login);
    }

    @Override
    public void LoginSuccess() {
        Toast.makeText(getApplicationContext(), "login finish", Toast.LENGTH_SHORT).show();

    }
}
