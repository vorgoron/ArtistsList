package com.vorgoron.artistslist;

import android.app.Application;

import com.activeandroid.sebbia.ActiveAndroid;
import com.vorgoron.artistslist.di.component.ApplicationComponent;
import com.vorgoron.artistslist.di.component.DaggerApplicationComponent;
import com.vorgoron.artistslist.di.module.ApplicationModule;
import com.vorgoron.artistslist.di.module.NetModule;

import lombok.Getter;

public class ArtistsApplication extends Application {

    @Getter
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // инициализия компонента dagger2
        applicationComponent = initComponent();
        // инициализия Active Android sebbia
        initActiveAndroid();
    }

    public ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(Constants.ENDPOINT_ADDRESS))
                .build();
    }

    private void initActiveAndroid() {
        ActiveAndroid.initialize(this);
        if (BuildConfig.DEBUG) {
            ActiveAndroid.setLoggingEnabled(true);
        }
    }
}
