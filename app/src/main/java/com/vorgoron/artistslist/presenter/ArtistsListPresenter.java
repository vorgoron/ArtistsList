package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import com.vorgoron.artistslist.ArtistsApplication;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.view.ArtistsListActivity;

import javax.inject.Inject;

/**
 * Презентер, управляющий списком исполнителей
 */
public class ArtistsListPresenter extends BasePresenter<ArtistsListActivity> {

    private static final int LOAD_ARTISTS = 1;

    @Inject
    DataManager dataManager;

    private boolean forceLoad;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        ArtistsApplication.getApplicationComponent().inject(this);
        // инициализация задачи загрузки исполнителей с кешированием последнего запроса
        restartableLatestCache(LOAD_ARTISTS,
                () -> dataManager.getArtists(forceLoad)
                        .compose(applySchedulers()),
                (artistsListActivity, artists) -> {
                    artistsListActivity.showProgress(false);
                    if (artists != null && !artists.isEmpty()) {
                        artistsListActivity.showList();
                        artistsListActivity.setArtists(artists);
                    } else {
                        artistsListActivity.showEmptyList();
                    }
                },
                (artistsListActivity, throwable) -> {
                    artistsListActivity.showReattemptGroup();
                    artistsListActivity.onError(throwable);
                });
    }

    /**
     * Загрузить список исполнителей
     *
     * @param artistsListActivity представление
     */
    public void loadArtists(ArtistsListActivity artistsListActivity, boolean forceLoad) {
        this.forceLoad = forceLoad;
        start(LOAD_ARTISTS);
        artistsListActivity.showProgress(true);
    }
}
