package com.nairbspace.retrofitdaggerexample;

import android.app.Application;
import android.content.Context;

import com.nairbspace.retrofitdaggerexample.dagger.AppComponent;
import com.nairbspace.retrofitdaggerexample.dagger.DaggerAppComponent;
import com.nairbspace.retrofitdaggerexample.dagger.NetworkModule;

public class SetupApplication extends Application {

    private AppComponent mAppComponent;

    public static SetupApplication get(Context context) {
        return (SetupApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
