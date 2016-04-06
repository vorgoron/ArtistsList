package com.vorgoron.artistslist.di.component;

import android.app.Application;

import com.vorgoron.artistslist.di.module.ApplicationModule;
import com.vorgoron.artistslist.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    Application providesApplication();

    Retrofit providesRetrofit();
}
