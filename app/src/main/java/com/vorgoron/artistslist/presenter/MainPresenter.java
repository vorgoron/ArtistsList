package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.view.BaseActivity;
import com.vorgoron.artistslist.view.MainActivity;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainActivity> {

    public static final int GET_ARTISTS = 1;

    @Inject
    ArtistApi artistApi;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableFirst(GET_ARTISTS,
                () -> artistApi.getArtists()
                        .compose(applySchedulers()),
                MainActivity::setArtists,
                BaseActivity::onError);
    }

    @Override
    public void injectPresenter(MainActivity mainActivity) {
        ((ArtistsApplication) mainActivity.getApplication()).getApplicationComponent().inject(this);
    }
}
