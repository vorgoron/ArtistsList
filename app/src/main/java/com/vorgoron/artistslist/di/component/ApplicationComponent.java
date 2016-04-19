package com.vorgoron.artistslist.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.vorgoron.artistslist.di.module.ApplicationModule;
import com.vorgoron.artistslist.di.module.NetModule;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.presenter.ArtistsDetailPresenter;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
}
