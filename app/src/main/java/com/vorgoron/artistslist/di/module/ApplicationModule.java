package com.vorgoron.artistslist.di.module;

import android.app.Application;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.Cache;

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
    Application provideApplication() {
        return artistsApplication;
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return new Cache();
    }
    
}
