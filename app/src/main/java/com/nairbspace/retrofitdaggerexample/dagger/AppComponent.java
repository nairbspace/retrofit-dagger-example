package com.nairbspace.retrofitdaggerexample.dagger;

import com.nairbspace.retrofitdaggerexample.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
