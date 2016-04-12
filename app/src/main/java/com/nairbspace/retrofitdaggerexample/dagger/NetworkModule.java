package com.nairbspace.retrofitdaggerexample.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nairbspace.retrofitdaggerexample.retrofit.ExampleInterceptor;
import com.nairbspace.retrofitdaggerexample.retrofit.ExampleInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    ExampleInterceptor provideInterceptor() {
        return ExampleInterceptor.get();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(ExampleInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("http://localhost/"); // Dummy Base URL needed to create instance
    }

    @Provides
    @Singleton
    ExampleInterface provideExampleInterface(Retrofit.Builder builder) {
        return builder.build().create(ExampleInterface.class);
    }
}
