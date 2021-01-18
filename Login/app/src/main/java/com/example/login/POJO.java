package com.example.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.Toast;

public class POJO extends BaseObservable {
    public String firstName;
    public String lastName;
    private boolean checked;



    Context context;
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;

    }


  //  public ObservableField<String> srcs=new ObservableField<>("http://18.236.244.149/dev/public/uploads/gas_station/default.png");


    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }


    @BindingAdapter("app:srcDrawable")
    public static void setImageDrawable(ImageView view,Drawable drawable) {
        view.setImageDrawable(drawable);
    }
}
