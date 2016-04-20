package com.vorgoron.artistslist.di.component;

import com.vorgoron.artistslist.di.module.ApplicationModule;
import com.vorgoron.artistslist.di.module.NetModule;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.model.ConnectionManager;
import com.vorgoron.artistslist.presenter.ArtistsDetailPresenter;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент для Dagger
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

//    Application providesApplication();
//    Gson provideGson();
//    OkHttpClient provideOkHttpClient();
//    Retrofit providesRetrofit();

    void inject(ArtistsListPresenter artistsListPresenter);

    void inject(ArtistsDetailPresenter artistsDetailPresenter);

    void inject(DataManager dataManager);

    void inject(ConnectionManager connectionManager);
}
