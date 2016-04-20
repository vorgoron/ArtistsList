package com.vorgoron.artistslist.model;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DataManager {

    @Inject
    ArtistApi artistApi;

    @Inject
    Cache cache;

    @Inject
    ConnectionManager connectionManager;

    @Inject
    public DataManager() {
        ArtistsApplication.getApplicationComponent().inject(this);
    }

    /**
     * Получить список исполнителей
     *
     * @return список исполнителей
     */
    public Observable<List<Artist>> getArtists(boolean forceLoad) {
        if (forceLoad && connectionManager.isInternetConnected()) {
            return artistApi
                    .getArtists()
                    .flatMap(cache::saveArtists);
        } else {
            return cache
                    .getArtists();
        }
    }

}
