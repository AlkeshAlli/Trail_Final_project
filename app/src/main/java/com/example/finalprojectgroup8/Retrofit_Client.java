package com.example.finalprojectgroup8;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Client {

    private static Retrofit_Client instance;

    private Retrofit retrofit;

    private Retrofit_Client(){
        retrofit = new Retrofit.Builder().baseUrl("https://b0c88e88.ngrok.io/").
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized  Retrofit_Client getInstance() {
        if (instance == null)
        { instance = new Retrofit_Client();}
        return instance;
    }
    public Node_Interface getApi() {

        return retrofit.create(Node_Interface.class);
    }
}
