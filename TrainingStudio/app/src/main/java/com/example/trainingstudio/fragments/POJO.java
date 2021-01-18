package com.example.trainingstudio.fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class POJO implements Parcelable {

    private String name;
    private String pass;

    protected POJO(Parcel in) {
        name = in.readString();
        pass = in.readString();
    }
    public POJO(){

    }
    public static final Creator<POJO> CREATOR = new Creator<POJO>() {
        @Override
        public POJO createFromParcel(Parcel in) {
            return new POJO(in);
        }

        @Override
        public POJO[] newArray(int size) {
            return new POJO[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pass);
    }
}
