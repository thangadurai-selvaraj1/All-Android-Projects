package com.example.retrofit;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofit.Api.BASE_URL;

public class MainViewModel extends BaseObservable {
    MainInterface mainInterface;
    Model model;
    Retrofit retrofit;
    List<Model> list;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;





    public MainViewModel(MainInterface mainInterface, Model model) {
        this.mainInterface = mainInterface;
        this.model = model;

    }

    }

