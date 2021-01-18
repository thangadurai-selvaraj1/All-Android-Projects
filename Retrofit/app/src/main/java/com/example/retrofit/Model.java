package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
