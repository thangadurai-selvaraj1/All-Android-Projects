package com.example.retrofit;

public class ApiConfig {



    private ApiConfig() {

    }

    private static final ApiConfig ourInstance = new ApiConfig();

    public static ApiConfig getInstance() {
        return ourInstance;
    }



}
