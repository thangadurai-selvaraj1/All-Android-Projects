package com.example.trainingstudio.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import com.example.trainingstudio.interfaces.LoginInterface;
import com.example.trainingstudio.model.Login;

public class LoginViewModel extends BaseObservable {

    private LoginInterface  loginInterface;
    private Login login;
    public ObservableField<String> sUserNameError = new ObservableField<>();
    public ObservableField<String> sPasswordError = new ObservableField<>();

    public LoginViewModel(LoginInterface loginInterface, Login login) {
        this.loginInterface = loginInterface;
        this.login = login;
    }

    public void onLoginClick(View view) {
        if (TextUtils.isEmpty(login.getUsername())) {
            sUserNameError.set("Required !");
        } else if (TextUtils.isEmpty(login.getPassword())) {
            sPasswordError.set("Required !");
        } else {
            sUserNameError.set("");
            sPasswordError.set("");
            loginInterface.LoginSuccess();
        }
    }

    @BindingAdapter("android:errorText")
    public static void setErrorText(TextInputLayout view, String message) {
        view.setError(message);
    }

}
