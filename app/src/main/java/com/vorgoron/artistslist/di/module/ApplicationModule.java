package com.vorgoron.artistslist.di.module;

import android.app.Application;
import android.content.Context;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.ConnectionManager;
import com.vorgoron.artistslist.model.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    public final ArtistsApplication artistsApplication;

    public ApplicationModule(ArtistsApplication artistsApplication) {
        this.artistsApplication = artistsApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return artistsApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return artistsApplication;
    }

    @Provides
    @Singleton
    ConnectionManager provideConnectionManager(Context context) {
        return new ConnectionManager(context);
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return new Cache();
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager();
    }
    
}
