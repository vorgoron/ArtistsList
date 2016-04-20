package com.vorgoron.artistslist;

import com.vorgoron.artistslist.di.component.ApplicationComponent;
import com.vorgoron.artistslist.di.component.DaggerTestApplicationComponent;
import com.vorgoron.artistslist.di.module.TestApplicationModule;
import com.vorgoron.artistslist.di.module.TestNetModule;


/**
 * Приложение для тестов.
 */
public class TestApplication extends ArtistsApplication {

    @Override
    public ApplicationComponent initComponent() {

        return DaggerTestApplicationComponent.builder()
                .testApplicationModule(new TestApplicationModule(this))
                .testNetModule(new TestNetModule())
                .build();
    }
}
