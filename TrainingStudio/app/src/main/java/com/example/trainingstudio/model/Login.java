package com.example.trainingstudio.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trainingstudio.BR;
import com.example.trainingstudio.db.ThemesDetail;

public class Login extends BaseObservable {

    static ThemesDetail themesDetail;
    private String username;
    private String password;

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @BindingAdapter("app:srcDrawable")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }
    @BindingAdapter("app:color")
    public static void setColor(TextView view, String color) {
       view.setBackgroundColor(Color.parseColor(color));
    }
}
