package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.view.ArtistsDetailActivity;
import com.vorgoron.artistslist.view.BaseActivity;

import javax.inject.Inject;

public class ArtistsDetailPresenter extends BasePresenter<ArtistsDetailActivity> {

    public static final int LOAD_ARTISTS = 1;

    @Inject
    Cache cache;

    private int artistId;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        ArtistsApplication.getApplicationComponent().inject(this);

        restartableFirst(LOAD_ARTISTS,
                () -> cache
                        .getArtist(artistId)
                        .compose(applySchedulers()),
                (activity, artist) -> {
                    activity.setArtistName(artist.getName());
                    activity.setImage(artist.getCover().getBig());
                    activity.setGenre(artist.getGenres());
                    String albumsString = activity.getResources().getQuantityString(R.plurals.albums,
                            artist.getAlbums(), artist.getAlbums());
                    String tracksString = activity.getResources().getQuantityString(R.plurals.tracks,
                            artist.getTracks(), artist.getTracks());
                    activity.setSumary(albumsString + " • " + tracksString);
                    activity.setDescription(artist.getDescription());
                },
                BaseActivity::onError);
    }

    public void loadArtist(int artistId) {
        this.artistId = artistId;
        start(LOAD_ARTISTS);
    }
}
