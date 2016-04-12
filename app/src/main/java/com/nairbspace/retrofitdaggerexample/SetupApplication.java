package com.nairbspace.retrofitdaggerexample;

import android.app.Application;
import android.content.Context;

import com.nairbspace.retrofitdaggerexample.dagger.AppComponent;
import com.nairbspace.retrofitdaggerexample.dagger.DaggerAppComponent;
import com.nairbspace.retrofitdaggerexample.dagger.NetworkModule;

public class SetupApplication extends Application {

    private AppComponent mAppComponent;

    // Convenience method for getting application context
    public static SetupApplication get(Context context) {
        return (SetupApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create instance of AppComponent
        mAppComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    // Getter for AppComponent
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
