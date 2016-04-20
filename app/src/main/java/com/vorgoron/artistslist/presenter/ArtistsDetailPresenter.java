package com.vorgoron.artistslist.presenter;

import android.os.Bundle;
import android.view.View;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.view.ArtistsDetailActivity;
import com.vorgoron.artistslist.view.BaseActivity;

import javax.inject.Inject;

import lombok.Getter;

/**
 * Презентер, управляющий отображением детальной информации об исполнителе.
 */
public class ArtistsDetailPresenter extends BasePresenter<ArtistsDetailActivity> {

    public static final int LOAD_ARTISTS = 1;

    @Inject
    DataManager dataManager;

    private int artistId;
    @Getter
    private String link;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        ArtistsApplication.getApplicationComponent().inject(this);

        // инициализация загрузки детальной информации об исполнителе с кешированием последнего запроса
        restartableLatestCache(LOAD_ARTISTS,
                () -> dataManager
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
                    activity.setSummary(activity.getString(R.string.artists_detail_summary, albumsString, tracksString));
                    activity.setDescription(artist.getDescription());
                    link = artist.getLink();
                    activity.setFabVisibility(link != null ? View.VISIBLE : View.GONE);
                    activity.showProgress(false);
                },
                BaseActivity::onError);
    }

    /**
     * Загрузка детальной информации об исполнителе
     *
     * @param artistId id исполнителя
     */
    public void loadArtist(int artistId) {
        this.artistId = artistId;
        start(LOAD_ARTISTS);
    }
}
