package com.vorgoron.artistslist.di.module;

import android.app.Application;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.TestApplication;
import com.vorgoron.artistslist.model.Cache;

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
    Application provideApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return Mockito.mock(Cache.class);
    }
}
