package com.example.barcode;



public class Model  {

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
