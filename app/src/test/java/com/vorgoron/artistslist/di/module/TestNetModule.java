package com.vorgoron.artistslist.di.module;

import com.vorgoron.artistslist.model.api.ArtistApi;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestNetModule {

    public TestNetModule() {
    }

    @Provides
    @Singleton
    ArtistApi provideArtistApi() {
        return Mockito.mock(ArtistApi.class);
    }
}
