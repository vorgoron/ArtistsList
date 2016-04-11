package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.view.ArtistsListActivity;

import javax.inject.Inject;

public class ArtistsListPresenter extends BasePresenter<ArtistsListActivity> {

    public static final int GET_ARTISTS = 1;

    @Inject
    ArtistApi artistApi;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableFirst(GET_ARTISTS,
                () -> artistApi.getArtists()
                        .compose(applySchedulers()),
                (artistsListActivity, artists) -> {
                    artistsListActivity.hideProgress();
                    artistsListActivity.setArtists(artists);
                },
                (artistsListActivity, throwable) -> {
                    artistsListActivity.showReattemptGroup(true);
                    artistsListActivity.onError(throwable);
                });
    }

    @Override
    public void injectPresenter(ArtistsListActivity artistsListActivity) {
        ((ArtistsApplication) artistsListActivity.getApplication()).getApplicationComponent().inject(this);
    }

    public void loadArtists(ArtistsListActivity artistsListActivity) {
        start(ArtistsListPresenter.GET_ARTISTS);
        artistsListActivity.showProgress();
        artistsListActivity.showReattemptGroup(false);
    }
}
