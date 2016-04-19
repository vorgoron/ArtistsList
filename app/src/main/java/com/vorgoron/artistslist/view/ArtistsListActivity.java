package com.vorgoron.artistslist.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.adapter.SimpleDividerItemDecoration;
import com.vorgoron.artistslist.model.api.response.Artist;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;

/**
 * Активити, отображающая список исполнителей
 */
@RequiresPresenter(ArtistsListPresenter.class)
public class ArtistsListActivity extends BaseActivity<ArtistsListPresenter> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /**
     * Блок с кнопкой повторной загрузки исполнителей (отображается
     * при отсутствии подлкючения к сети)
     */
    @Bind(R.id.reattempt_group)
    View reattemptGroup;
    /**
     * View для отображения списка исполнителей
     */
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.progress_bar)
    View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);

        setSupportActionBar(toolbar);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new SimpleDividerItemDecoration(this));
        list.setHasFixedSize(true);

        getPresenter().loadArtists(this);
    }

    /**
     * Повторить загрузку
     */
    @OnClick(R.id.btn_retry_to_load)
    public void retryLoad() {
        getPresenter().loadArtists(this);
    }

    /**
     * Заполнить список исполнителей
     *
     * @param artists список исполнителей
     */
    public void setArtists(List<Artist> artists) {
        ArtistAdapter artistAdapter = new ArtistAdapter(this, artists);
        artistAdapter.setOnItemClickListener(this::onClickArtist);
        list.setAdapter(artistAdapter);
    }

    /**
     * При нажатии на позицию исполнителя открываем активити
     * с детальной информацией
     *
     * @param artist выбранный исполнитель
     */
    private void onClickArtist(Artist artist) {
        ArtistsDetailActivity.launch(this, artist.getArtistId());
    }

    /**
     * Показать блок с кнопкой повторной загрузки исполнителей
     *
     * @param show показать
     */
    public void showReattemptGroup(boolean show) {
        reattemptGroup.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Реализация метода отображения индикатора загрузки
     * @param show
     */
    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
