package com.example.login;


import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class Model extends BaseObservable {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
      //  notifyPropertyChanged(BR.name);

    }

    public Model(String name) {
        this.name = name;
    }
}
