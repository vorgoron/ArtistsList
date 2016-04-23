package com.vorgoron.artistslist.di.module;

import android.app.Application;
import android.content.Context;

import com.vorgoron.artistslist.TestApplication;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.ConnectionManager;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.model.api.ArtistApi;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestApplicationModule {

    public final TestApplication testApplication;

    public TestApplicationModule(TestApplication testApplication) {
        this.testApplication = testApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return testApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    ConnectionManager provideConnectionManager(Context context) {
        return Mockito.mock(ConnectionManager.class);
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return Mockito.mock(Cache.class);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(Context context,
                                   ArtistApi artistApi,
                                   Cache cache,
                                   ConnectionManager connectionManager) {
        return Mockito.mock(DataManager.class);
    }
}
