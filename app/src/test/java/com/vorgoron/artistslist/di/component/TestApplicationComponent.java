package com.vorgoron.artistslist.di.component;

import com.vorgoron.artistslist.di.module.TestApplicationModule;
import com.vorgoron.artistslist.di.module.TestNetModule;
import com.vorgoron.artistslist.model.ArtistApiTest;
import com.vorgoron.artistslist.model.DataManagerTest;
import com.vorgoron.artistslist.view.ArtistListActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент для Dagger
 */
@Singleton
@Component(modules = {TestApplicationModule.class, TestNetModule.class})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(ArtistApiTest artistApiTest);

    void inject(ArtistListActivityTest artistListActivityTest);

    void inject(DataManagerTest dataManagerTest);
}
