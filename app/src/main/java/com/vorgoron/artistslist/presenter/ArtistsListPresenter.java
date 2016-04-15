package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.view.ArtistsListActivity;

import javax.inject.Inject;

public class ArtistsListPresenter extends BasePresenter<ArtistsListActivity> {

    public static final int LOAD_ARTISTS = 1;
    private static final int GET_ARTISTS_FROM_CACHE = 2;

    @Inject
    ArtistApi artistApi;
    @Inject
    Cache cache;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        ArtistsApplication.getApplicationComponent().inject(this);

        restartableFirst(LOAD_ARTISTS,
                () -> artistApi.getArtists()
                        .doOnNext(cache::saveArtists)
                        .flatMap(artists1 -> cache.getArtists())
                        .compose(applySchedulers()),
                (artistsListActivity, artists) -> {
                    artistsListActivity.hideProgress();
                    artistsListActivity.setArtists(artists);
                },
                (artistsListActivity, throwable) -> {
                    artistsListActivity.showReattemptGroup(true);
                    artistsListActivity.onError(throwable);
                });


        restartableFirst(GET_ARTISTS_FROM_CACHE,
                () -> cache.getArtists()
                        .compose(applySchedulers()),
                (artistsListActivity, artists) -> {
                    if (artists != null && !artists.isEmpty()) {
                        artistsListActivity.hideProgress();
                        artistsListActivity.setArtists(artists);
                    } else {
                        start(LOAD_ARTISTS);
                    }
                },
                (artistsListActivity, throwable) -> {
                    artistsListActivity.showReattemptGroup(true);
                    artistsListActivity.onError(throwable);
                });
    }

    public void loadArtists(ArtistsListActivity artistsListActivity) {
        start(ArtistsListPresenter.GET_ARTISTS_FROM_CACHE);
        artistsListActivity.showProgress();
        artistsListActivity.showReattemptGroup(false);
    }
}
