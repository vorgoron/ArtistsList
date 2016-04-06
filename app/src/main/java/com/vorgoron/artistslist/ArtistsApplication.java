package com.vorgoron.artistslist;

import android.app.Application;

import com.vorgoron.artistslist.di.component.ApplicationComponent;
import com.vorgoron.artistslist.di.component.DaggerApplicationComponent;
import com.vorgoron.artistslist.di.module.ApplicationModule;

import lombok.Getter;

public class ArtistsApplication extends Application {

    @Getter
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
