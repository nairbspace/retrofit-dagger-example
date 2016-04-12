package com.nairbspace.retrofitdaggerexample.retrofit;

import com.nairbspace.retrofitdaggerexample.retrofit.model.ExampleModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExampleInterface {

    @GET("/")
    Call<ExampleModel> get();
}

