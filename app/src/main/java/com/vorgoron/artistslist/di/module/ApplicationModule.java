package com.vorgoron.artistslist.di.module;

import android.app.Application;

import com.vorgoron.artistslist.ArtistsApplication;

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
    
}
