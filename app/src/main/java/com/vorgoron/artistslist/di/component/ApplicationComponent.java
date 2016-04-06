package com.vorgoron.artistslist.di.component;

import android.app.Application;

import com.vorgoron.artistslist.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application providesApplication();

}
