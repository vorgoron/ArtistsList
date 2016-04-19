package com.vorgoron.artistslist.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.vorgoron.artistslist.di.module.TestApplicationModule;
import com.vorgoron.artistslist.di.module.TestNetModule;
import com.vorgoron.artistslist.model.ArtistApiTest;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.CacheTest;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.presenter.ArtistsDetailPresenter;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;
import com.vorgoron.artistslist.view.ArtistListActivityTest;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Компонент для Dagger
 */
@Singleton
@Component(modules = {TestApplicationModule.class, TestNetModule.class})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(ArtistApiTest artistApiTest);

    void inject(CacheTest cacheTest);

    void inject(ArtistListActivityTest artistListActivityTest);
}
