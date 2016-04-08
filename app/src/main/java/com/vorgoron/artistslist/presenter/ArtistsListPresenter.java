package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.view.ArtistsListActivity;
import com.vorgoron.artistslist.view.BaseActivity;

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
                ArtistsListActivity::setArtists,
                BaseActivity::onError);
    }

    @Override
    public void injectPresenter(ArtistsListActivity artistsListActivity) {
        ((ArtistsApplication) artistsListActivity.getApplication()).getApplicationComponent().inject(this);
    }
}
